# 算法描述
# 当前此算法实现的视野只有目前这一段落，和上一个段落的走势，以及base值
# 在滑动窗口内，取得峰值（最大值且满足染色条件），若连续峰值差距比较大则认为买入/卖出机会存在
# 8.16 优化，存在整理区间，设置区间系数，大于25开始做单, 比较有效，能够杜绝反转趋势。负面影响：1. 来自对放的力量无法识别 2. 错失交易机会
# 8.16 优化，峰值一定要是实现，并且大于虚线 ，所以base与max差距不要太大
# 8.17 重要优化，在base和当前柱，应该加入价格的比较，能影响价格，说明有效；但是也可能会背离 [ 这个需要进一步考虑 ]
# 8.17 优化，滑动窗口设置为50会更好，待确认
# 8.18 优化未完成，5~8，左侧开单 8~10，右侧开单
# 8.30 增加刺穿回补功能，解决重复开单问题——设置时间，开单后叠加3h（后面要改为动态）sto
# 8.30 危险区域预警，未完成
# 9.2 把这个程序分为两段：1、做数据采集{} 2、做数据分析
# 9.6 确认down_trend、down_counter 这个两个值的关系；trend 要拉长这个振幅，所以肯定要大于下面的counter
# 9.7 修改 trend，加入多币种进行管理
import time
import datetime
import json


# 参数设置
# 参数1： window_length 关注的k线数量 40
# 参数2： my_period 自定义周期 3600000
# 参数4： balance_init 初始资金 50000
# 参数5： 初始币 3
# 参数6   margin_level 杠杆倍数 80
# 参数7   margin_every 每单保证金 100

class Order:
    def __init__(self):
        self.orderId = ""
        self.crypto = ""
        self.price = ""  # 开单的价格
        self.stop_loss_price = ""  # 止损价格
        self.stop_profit_price = ""  # 止盈价格
        self.earnings = ""  # 每单收益状况
        self.direction = ""  # 开单方向
        self.amount = ""  # 开单的数量
        self.startTime = ""  # 开始运行的时间
        self.margin = ""  # 默认保证金一百u
        self.out = False


def atr():
    r = exchange.GetRecords(PERIOD_M30)
    atr = TA.ATR(r, 14)


# 1 每单设置固定的份额，一百u，如果有相当强大的暗示，可以适当可以设置为200u作为起始资金
# 2 只负责做空，做多
def onTick(args, time_level, margin_every):
    exchange.SetMarginLevel(20)
    o = Order()
    ticker = _C(exchange.GetTicker)
    account = _C(exchange.GetAccount)
    crypto = exchange.GetCurrency()
    timestamp = time.time()
    o.startTime = timestamp
    o.crypto = crypto
    last_price = ticker.Last
    # 更改不设置
    if args['dirct'] == 'long' and last_price > 1000:  # 买入
        o.direction = args['dirct']
        position = exchange.GetPosition()
        price = ticker.Sell
        orderId = exchange.SetDirection("buy")
        orderId = exchange.Buy(-1, margin_every * margin_level // price)
        o.price = price
        # 这里根据atr指标设置购买进去的区间
        o.margin = margin_every  # 默认保证金一百u
        o.orderId = orderId
        o.amount = margin_every * margin_level // price
        o.stop_loss_price = price * (1 - 0.01)
        o.stop_profit_price = price * (1 + env[crypto]['atr_factor'])
        o.earnings = 0
        Log("多单开单成功: 订单ID", orderId, "现在价格", o.price, "止损价格", o.stop_loss_price, "止盈价格", o.stop_profit_price)

    if args['dirct'] == 'short' and last_price > 1000:
        o.direction = args['dirct']
        price = ticker.Buy  # 再确保一下是不是买一价
        position = exchange.GetPosition()
        orderId = exchange.SetDirection("sell")
        orderId = exchange.Sell(-1, margin_every * margin_level // price)
        o.price = price
        o.margin = margin_every  # 默认保证金一百u
        o.orderId = orderId
        o.amount = margin_every * margin_level // price
        o.stop_loss_price = price * (1 + 0.01)
        o.stop_profit_price = price * (1 - env[crypto]['atr_factor'])
        o.earnings = 0
        Log("空单开单成功: 订单ID", orderId, "现在价格", o.price, "止损价格", o.stop_loss_price, "止盈价格", o.stop_profit_price)
    return o  # 返回订单方便订单管理


# 订单管理模块功能
# 1：循环扫描订单是否超时，比如一个5m的订单，再一个一小时的大级别过去后，行情是否已经发生了变化，是否止盈,由于惯性思维，暴露三根k线，然后检测订单盈利情况
# 2：判断订单是否成功止损，止盈


# 移动
def move_harvest_profits2(orderId_list, margin_every):
    crypto = exchange.GetCurrency()
    ticker = _C(exchange.GetTicker)
    for i in range(0, len(orderId_list)):
        if orderId_list[i].direction == 'long':
            now_price = ticker.Sell
            radio = (ticker.Sell - orderId_list[i].price) / orderId_list[i].price
            earnings = radio * orderId_list[i].margin * margin_level
            if orderId_list[i].crypto == crypto and radio > 0.005:
                # 如果比率为盈（正为盈利），拔高止损价格为1+radio
                orderId_list[i].stop_loss_price = orderId_list[i].stop_loss_price * (1 + radio)
                # 调试使用
                # Log("订单，止损价格调高：", orderId_list[i].orderId, orderId_list[i].stop_loss_price)
        if orderId_list[i].direction == 'short':
            now_price = ticker.Buy
            radio = (ticker.Buy - orderId_list[i].price) / orderId_list[i].price
            earnings = radio * orderId_list[i].margin * margin_level
            if orderId_list[i].crypto == crypto and radio < -0.005:
                # 如果比率为盈(负为盈利)，拔高止损价格为1+radio (radio本身带个负，所以其实是相减)
                orderId_list[i].stop_loss_price = orderId_list[i].stop_loss_price * (1 + radio)
                # Log("订单，止损价格降低：", orderId_list[i].orderId, orderId_list[i].stop_loss_price)


# 根据币价止盈
def harvest_profits2(orderId_list, margin_every):
    index_1 = []
    index_2 = []
    crypto = exchange.GetCurrency()
    ticker = _C(exchange.GetTicker)
    #Sleep(env['sleeptime'])
    for i in range(0, len(orderId_list)):
        if orderId_list[i].direction == 'long':
            now_price = ticker.Sell
            radio = (now_price - orderId_list[i].price) / orderId_list[i].price
            if orderId_list[i].crypto == crypto and now_price >= orderId_list[i].stop_profit_price:
                force_out("long", orderId_list[i], now_price)
                index_1.append(orderId_list[i])

        if orderId_list[i].direction == 'short':
            now_price = ticker.Buy
            radio = (ticker.Buy - orderId_list[i].price) / orderId_list[i].price
            if orderId_list[i].crypto == crypto and now_price <= orderId_list[i].stop_profit_price:  # 因为是空所以得向下看
                force_out("short", orderId_list[i], now_price)
                index_2.append(orderId_list[i])
    return (index_1, index_2)


# 止损模块
def stop_lose(orderId_list):
    crypto = exchange.GetCurrency()
    ticker = _C(exchange.GetTicker)
    
    #止损模块出了点问题
    if len(orderId_list) > 2:
        now_price = ticker.Last
        for i in range(0,len(orderId_list) - 1):  
            if orderId_list[i].direction == 'long':
                radio = (now_price - orderId_list[i].price) / orderId_list[i].price
                earnings = radio * orderId_list[i].margin * margin_level
                if orderId_list[i].crypto == crypto and now_price <= orderId_list[i].stop_loss_price:
                    force_out("long", orderId_list[i],now_price)
                    
            if orderId_list[i].direction == 'short':
                radio =  (-1) * (now_price - orderId_list[i].price) / orderId_list[i].price
                earnings = radio * orderId_list[i].margin * margin_level
                if orderId_list[i].crypto == crypto and now_price >= orderId_list[i].stop_loss_price:
                    force_out("short", orderId_list[i],now_price)


def abs_stop_lose(orderId_list):
    crypto = exchange.GetCurrency()
    ticker = _C(exchange.GetTicker)

    for order in orderId_list:
        if order.direction == 'long':
            now_price = ticker.Last
            radio = (ticker.Last - order.price) / order.price
            if radio * margin_level < -1.5:
                force_out("long", order, now_price)

        elif order.direction == 'short':
            now_price = ticker.Last
            radio = -1 * (ticker.Last - order.price) / order.price
            if radio * margin_level < -1.5:
                force_out("short", order, now_price)

# 得到订单盈利情况
def get_order_profit(orderId_list, direction):
    args_list = []
    args = {'dirct': None, 'profit': None, 'orderId': None}

    if dirction == "long":  # 检测多单
        ticker = _C(exchange.GetTicker)
        price = ticker.Sell
        for i in len(0, len(orderId_list)):
            rate = (price - orderId_list[i].price) / orderId_list[i].price
            priot = orderId_list[i].Margin * margin_level * rate
            nowTime = time.time()
            if nowTime - orderId_list[i].startTime > 4800 and priot < 0:  # 大于一个阈值
                args['dirct'] = "long"
                args['profit'] = priot
                args["orderId"] = orderId_list[i].orderId
            args_list.append(args)

        return args_list  # 订单长期未平仓，并出现了大规模利润亏损的情况

    if dirction == "short":  # 检测多单
        ticker = _C(exchange.GetTicker)
        price = ticker.Buy
        for i in len(0, len(orderId_list)):
            rate = (orderId_list[i].price - price) / orderId_list[i].price
            priot = orderId_list[i].Margin * margin_level * rate
            nowTime = time.time()
            if nowTime - orderId_list[i].startTime > 4800 and priot < 0:  # 大于一个阈值
                args['dirct'] = "short"
                args['profit'] = priot
                args["orderId"] = orderId_list[i].orderId

        return args_list  # 订单长期未平仓，并出现了大规模利润亏损的情况


def force_out(direction, order, now_price):
    if order.out:
        return
    if direction == "long": # 这里根据当前的价格可以判断是止盈还是止损
        radio = (now_price - order.price) / order.price
        earnings = radio * order.margin * margin_level / 1.35
        exchange.SetDirection("closebuy")
        exchange.Sell(-1, order.amount)
        if radio  >= env[order.crypto]['atr_factor']:  # 表示止盈
            trading_info['win_counter'] += 1
            Log("完全止盈多单", "orderId: ", order.orderId, "预期止盈价格：", order.stop_profit_price,"实际止盈价格：",now_price)
            Log("多单盈利", earnings )
        elif radio  < env[order.crypto]['atr_factor'] and  radio * margin_level >= 0: # 部分止盈
            trading_info['win_half_counter'] += 1
            Log("部分止盈多单", "orderId: ", order.orderId, "预期止盈价格：", order.stop_profit_price, "实际止盈价格：", now_price)
            Log("多单盈利", earnings )
        else:  # 表示止损
            trading_info['lose_counter'] += 1
            trading_info['lose_rate_list'].append(radio * margin_level)
            Log("止损多单", "orderId: ", order.orderId, "预期止损价格：", order.stop_profit_price, "实际止损价格：", now_price)
            Log("多单止损", earnings, "止损比例", radio * margin_level)
        trading_info['total_profit'] += earnings
        LogProfit(trading_info['total_profit'])
        order.out = True
        trading_info['maker_vol'] += 1
    # 可以报告损失多少盈利多少
    if direction == "short":
        radio = (-1) *(now_price - order.price) / order.price
        earnings = radio * order.margin * margin_level
        exchange.SetDirection("closesell")
        exchange.Buy(-1, order.amount)
        if radio >= env[order.crypto]['atr_factor']:  # 表示止盈
            trading_info['win_counter'] += 1
            Log("完全止盈空单", "orderId: ", order.orderId, "预期止盈价格：", order.stop_profit_price, "实际止盈价格：", now_price)
            Log("空单止盈", earnings)
        elif radio  < env[order.crypto]['atr_factor'] and  radio * margin_level >=0: # 部分止盈
            trading_info['win_half_counter'] += 1
            Log("部分止盈空单", "orderId: ", order.orderId, "预期止盈价格：", order.stop_profit_price, "实际止盈价格：", now_price)
            Log("空单止盈", earnings)
        else:  # 表示止损
            trading_info['lose_counter'] += 1
            trading_info['lose_rate_list'].append(radio * margin_level)
            Log("止损空单", "orderId: ", order.orderId, "预期止盈价格：", order.stop_profit_price, "实际止盈价格：", now_price)
            Log("空单止损", earnings, "止损比例", radio * margin_level)
        trading_info['total_profit'] += earnings
        LogProfit(trading_info['total_profit'])
        order.out = True
        trading_info['maker_vol'] += 1


def way_macd(args, macd_list, max, min):
    new_max = macd_list[0]
    new_min = macd_list[0]
    singal = 0  # 正常运行
    if args['dirct'] == 'long':
        # 判断最近4根线如果有两根超过了
        down_base = args['add_para']
        Log("down_base", down_base)
        Log("最新", macd_list[-1])
        if (macd_list[-1] / min) >= 1.5:
            # 报警，多单开始止损
            new_min = macd_list[-1]
            singal = 1
    if args['dirct'] == 'short':
        up_base = args['add_para']
        if (macd_list[-1] / max) >= 1.5:
            # 报警空单开始止损
            singal = -1
            new_max = macd_list[-1]
        Log("come on stop")
    return (singal, new_max, new_min)


def setting_dzone(idx, crypto, dirct, length):
    # 设置约束关系
    # 5m 不受约束
    if length < 1:
        return
    elif idx == "5m":
        if dirct == "up":
            if env[crypto]["5m_up_dzoneC"] < env[crypto]["5m_counter"]:
                env[crypto]["5m_up_dzoneC"] = env[crypto]["5m_counter"] + length
        elif dirct == "down":
            if env[crypto]["5m_down_dzoneC"] < env[crypto]["5m_counter"]:
                env[crypto]["5m_down_dzoneC"] = env[crypto]["5m_counter"] + length
    elif idx == "1h":
        # 1h 约束 5min, 和 自己
        if dirct == "up":  # 上行存在危险
            if env[crypto]["5m_up_dzoneC"] < env[crypto]["5m_counter"]:
                env[crypto]["5m_up_dzoneC"] = env[crypto]["5m_counter"] + length * 4
            if env[crypto]["1h_up_dzoneC"] < env[crypto]["1h_counter"]:
                env[crypto]["1h_up_dzoneC"] = env[crypto]["1h_counter"] + length
        elif dirct == "down":  # 下行存在危险
            if env[crypto]["5m_down_dzoneC"] < env[crypto]["5m_counter"]:
                env[crypto]["5m_down_dzoneC"] = env[crypto]["5m_counter"] + length * 4
            if env[crypto]["1h_down_dzoneC"] < env[crypto]["1h_counter"]:
                env[crypto]["1h_down_dzoneC"] = env[crypto]["1h_counter"] + length
    elif idx == "4h":  # 4h 约束 1h 和 自己， 并给出心跳建议
        if dirct == "up":
            if env[crypto]["1h_up_dzoneC"] < env[crypto]["1h_counter"]:
                env[crypto]["1h_up_dzoneC"] = env[crypto]["1h_counter"] + length * 4
            # env[crypto]["4h_up_dzoneC"] = env[crypto]["4h_counter"] + length
        elif dirct == "down":
            if env[crypto]["1h_down_dzoneC"] < env[crypto]["1h_counter"]:
                env[crypto]["1h_down_dzoneC"] = env[crypto]["1h_counter"] + length * 4
            # env[crypto]["4h_up_dzoneC"] = env[crypto]["4h_counter"] + length
    else:
        # 这里要形成提示和建议的
        pass


def get_macd(record):
    # Log("records length:", len(record))
    macd = TA.MACD(record, 13, 34, 9)
    # Log(macd)
    # Log("macd length:", len(macd))
    # Log("DIF:", macd[0][-1:])
    # Log("FAST:", macd[1][-1:])
    # Log("SLOW:", macd[2][-1:])
    macd_res = macd[2][-window_length:]
    macd_res = list(filter(None, macd_res))
    Sleep(env['sleeptime'])
    return macd_res


def scan_log(crypto, macd_info, idx):
    def window_ava(index):
        win_idx = index % window_length
        # 每个时间窗之内只做固定单数
        if win_idx not in env[crypto]:
            env[crypto][win_idx] = 0
        elif env[crypto][win_idx] > 8:
            env[crypto][win_idx] = 0
            return False
        else:
            env[crypto][win_idx] += 1

        return True

    def cross_over(idx, macd_info):
        # 初始化
        cur = macd_info.get("cur_" + idx)
        if (macd_info[idx]["abs_up_trend"] > 0 or macd_info[idx]["abs_down_trend"] == 1) and cur < 0:
            setting_dzone(idx, crypto, "down", 1)
            macd_info[idx]['cross_over'] = "active"
        elif (macd_info[idx]["abs_down_trend"] > 0 or macd_info[idx]["abs_up_trend"] == 1) and cur > 0:
            setting_dzone(idx, crypto, "up", 1)
            macd_info[idx]['cross_over'] = "active"
        return macd_info

    # cross over 写入 cross counter 不做单
    macd_info = cross_over(idx, macd_info)

    # 局部数据刷新
    args = {'dirct': None, 'crypto': None, 'price': None, 'price_range': None, 'ticket': idx,
            'core_factor': env[crypto]["trend_factor"],
            'add_para': None, 'method': "normal",
            'out_price': None}  # 1.方向 2.base值 3.止损价格
    cur = macd_info.get("cur_" + idx)
    macd_detail = macd_info[idx]

    # if not window_ava(macd_info[idx + "_counter"]):
    #     return args, macd_info[idx]


    # 刺穿回补应该被实时进行刷新
    if macd_detail['raw_data'][-1] / cur < 0:
        macd_detail['down_break_hole'] = 5
        macd_detail['up_break_hole'] = 5

    if cur > 0:
        if cur > macd_detail['raw_data'][-1]:
            now_color = 'UY'
        else:
            now_color = 'UN'
    else:
        if cur < macd_detail['raw_data'][-1]:
            now_color = 'DY'
        else:
            now_color = 'DN'

    up_level = '1h' if idx == '5m' else None
    down_level = '5m' if idx == '1h' else None

    # 只要有足够震荡，就可以发起极限挑战，无视危险区、无视mayday信号
    extreme = False
    if cur > 0 and macd_detail['up_base'] > 0:
        if abs(cur / macd_detail['up_base']) > 0.9:
            # 极限挑战
            extreme = True
            args['method'] = "extreme"
    elif cur < 0 and macd_detail['up_base'] < 0:
        if abs(cur / macd_detail['up_base']) > 0.9:
            # 极限挑战
            extreme = True
            args['method'] = "extreme"
    #Log("extreme",extreme)
    if macd_detail['up_counter'] >= 8 and macd_detail['up_counter'] <= 100 and now_color == 'UY' and cur < macd_detail[
        'up_base'] * 1.1 \
            and abs(cur) / abs(env[crypto]['price']) > env[crypto]['bal'] and macd_detail['deep_ops1']:
        # 刺穿不能开， allin可以开，没有时间限制可以开
        if macd_detail['up_break_hole'] <= 2:  # 不开单
            pass
        elif macd_info[idx + "_counter"] < macd_info[idx + "_up_dzone"] and extreme == False:  # 没有极限挑战才检验
            pass
        elif macd_info[idx + "_counter"] < env[crypto][idx + "_up_dzoneC"] and extreme == False:
            pass
        elif "up" in macd_detail['mayday'] and extreme == False:
            pass
        elif macd_detail['up_counter'] < 12 and macd_detail['abs_up_trend'] < 10:
            pass
        elif macd_info[idx + "_counter"] >= macd_info[idx + "_up_target_counter"]:
            args['dirct'] = 'short'
            args['add_para'] = macd_detail['up_base']
            args['out_price'] = macd_detail['price_high']
            macd_info[idx + "_up_target_counter"] = macd_info[idx + "_counter"] + 4
            if down_level:
                macd_info[down_level + "_down_dzone"] = macd_info[down_level + "_counter"] + 16 * 4
            # 获得价格
            args['price'] = abs(env[crypto]['price'])
            args['crypto'] = exchange.GetCurrency()
            Log("action! short : ", args, '@')
            # Log(macd_info)
        else:
            args['dirct'] = None
    if macd_detail['down_counter'] >= 8 and macd_detail['down_counter'] <= 100 and now_color == 'DY' and abs(cur) < abs(
            macd_detail['down_base'] * 1.1) \
            and abs(cur) / abs(env[crypto]['price']) > env[crypto]['bal'] and macd_detail['deep_ops1']:
        # refresh_factor
        if macd_detail['down_break_hole'] <= 2:  # 有限制不开单
            pass
        elif macd_info[idx + "_counter"] < macd_info[idx + "_down_dzone"] and extreme == False:
            pass
        elif "down" in macd_detail['mayday'] and extreme == False:
            pass
        elif macd_info[idx + "_counter"] < env[crypto][idx + "_down_dzoneC"] and extreme == False:
            pass
        elif macd_detail['down_counter'] < 12 and macd_detail['abs_down_trend'] < 10:
            pass
        elif macd_info[idx + "_counter"] >= macd_info[idx + "_down_target_counter"]:
            args['dirct'] = 'long'
            args['add_para'] = macd_detail['down_base']
            args['out_price'] = macd_detail['price_low']
            # 获得价格
            args['price'] = abs(env[crypto]['price'])
            args['crypto'] = exchange.GetCurrency()
            macd_info[idx + "_down_target_counter"] = macd_info[idx + "_counter"] + 4
            if down_level:
                macd_info[down_level + "_up_dzone"] = macd_info[down_level + "_counter"] + 16 * 4  # 1h下方到5min
            Log("action! long: ", args, '@')
            # Log(macd_info)
        else:
            args['dirct'] = None
    # 目前回写的了刺穿回补
    macd_info[idx] = macd_detail
    return args, macd_info[idx]


# def onexit():
#     beginTime = time.time() * 1000
#     while True:
#         ts = time.time() * 1000
#         Log("程序停止倒计时..扫尾开始，已经过去：", (ts - beginTime) / 1000, "秒！")
#         Sleep(1000)

# x_trend 表示当前到第一次试探的距离
# last_x_trend 表示当前到最后一次试探的距离
# x_counter 表示当前距离base的距离
# sec_up_base 表示次级的base

def get_macd_log(crypto, record, mode="", dzone_mode="hard"):
    debug = False
    
    #对dif能量柱进行标注嘛
    def make_color(diff_lst):
        color_list = []
        if diff_lst[0] > 0:
            color_list.append('UY')
        else:
            color_list.append('DY')
        for i in range(1, len(diff_lst)):
            if diff_lst[i] < 0:
                if diff_lst[i] < diff_lst[i - 1]:
                    color_list.append('DY')
                else:
                    color_list.append('DN')
            if diff_lst[i] > 0:
                if diff_lst[i] > diff_lst[i - 1]:
                    color_list.append('UY')
                else:
                    color_list.append('UN')
        return color_list

    macd_info = {}
    macd_lst = get_macd(record)

    # lazy 模式下不会把最后一根macd纳入分析，认为没有收线是不稳定的
    if mode == "lazy":
        # Log("before",macd_lst)
        macd_lst = macd_lst[0:-1]
        # Log("after",macd_lst)
    process = 4
    upmax = max(macd_lst)
    downmax = min(macd_lst)
    args = {'dirct': None, 'price_range': None, 'ticket': None, 'core_factor': None, 'add_para': None,
            'out_price': None}  # 1.方向 2.base值 3.止损价格

    core_factor = 50
    up_counter, down_counter = 0, 0
    histrend, current_trend, up_trend, down_trend = 0, 0, 0, 0
    up_trend_level, down_trend_level = 0, 0

    abs_up_trend, abs_down_trend = 0, 0
    up_break_hole, down_break_hole = 5, 5  # 0~5，0表示生效，5表示失效
    time_counter = 0
    patient_life = 1
    allin_life = 1

    # 计算macd状态和factor
    up_factor, down_factor = 0, 0
    up_dzone, down_dzone = 0, 0
    out_flag = ""

    sec_up_base, sec_down_base = 0, 0

    # 1. 获取时间窗
    if len(macd_lst) > window_length:
        macd_lst = macd_lst[-window_length:]
    color_lst = make_color(macd_lst)
    print("debuging color_lst: ", color_lst) if debug else debug
    # 2. 找出上行/下行，1s 2nd实线峰值，计算落差
    down_base = 0
    up_base = 0
    deep_ops1 = True

    for i in range(1, len(macd_lst)):
        # trend discover
        # print(out_flag)
        if "N" in color_lst[i]:
            out_flag = ""  # mayday不持续, 还是说要持续一段时间？mayday要求一直为实线
        # if "Y" in color_lst[i]:
        #     ex_flag = ""
        # print("down_trend", macd_lst[i], sec_up_base,out_flag)
        if sec_up_base > 0 and macd_lst[i] > sec_up_base * 1.1 and macd_lst[i] > 0: #and ex_flag != "extreme":  # 1号止损位置
            out_flag = "up_mayday"
            up_dzone = 1
            down_dzone = 0
            up_factor = 2.5
            down_trend = 0
            print("1号止损位置,up_mayday")
        if sec_down_base < 0 and abs(macd_lst[i]) > abs(sec_down_base) * 1.1 and macd_lst[i] < 0:  #and ex_flag != "extreme":
            out_flag = "down_mayday"
            down_dzone = 1
            up_dzone = 0
            down_factor = 2.5
            up_trend = 0
            print("1号止损位置,down_mayday")
        # print("up_counter",up_counter)

        # 开单场景 刷新sec upbase
        if 'UY' in color_lst[
            i - 1] and up_base > 0 and up_break_hole >= 3:  # and abs(macd_lst[i]) < abs(macd_lst[i-1]):  # 表示base存在之后，进行了第一次试探，并且收线了，认为可能开启下行趋势
            sec_up_base = macd_lst[i]
            if "up_mayday" not in out_flag:
                down_trend_level += 1
                down_trend = len(macd_lst) - i + 1
                print("down_trend start")
        if 'DY' in color_lst[
            i - 1] and down_base < 0 and down_break_hole >= 3:  # and abs(macd_lst[i]) < abs(macd_lst[i-1]):  # 表示base存在之后，进行了第一次试探，并且收线了，认为可能开启上行趋势
            sec_down_base = macd_lst[i]
            if "down_mayday" not in out_flag:  # 没有打止损，才开启
                up_trend_level += 1
                up_trend = len(macd_lst) - i + 1
                print("up_trend start")

        # 开单场景，extrem挑战者模式，认为逼近base开始，（默认) 未超过非实线结束表示守擂成功，；超过则认为守擂失败；
        # if "UY" in color_lst[i] and abs(macd_lst[i]) > 0.9 * up_base:
        #     ex_flag = "extreme"
        #     out_flag = ""
        # elif "DY" in color_lst[i] and abs(macd_lst[i]) > 0.9 * down_base:
        #     ex_flag = "extreme"
        #     out_flag = ""


        # print(ex_flag)

        # trend 超大则失效
        if up_trend > window_length * 0.6:
            up_trend = 0
            up_trend_level = 0
        if down_trend > window_length * 0.6:
            down_trend = 0
            down_trend_level = 0

        # print(down_trend)
        # abs_trend_discover
        if 'U' in color_lst[i]:
            down_break_hole = 5  # 进入上行后或者连续震荡，下行穿透失效
            abs_up_trend += 1
            abs_down_trend = 0
        elif 'D' in color_lst[i]:
            up_break_hole = 5  # 进入下行后或者连续震荡，上行穿透失效
            abs_down_trend += 1
            abs_up_trend = 0

        # 刺穿回补，震荡累加
        if down_break_hole < 5:
            down_break_hole += 1
        if up_break_hole < 5:
            up_break_hole += 1

        if "up_mayday" in out_flag:
            if abs_down_trend > 0:
                out_flag = ""
                up_dzone = 0
        if "down_mayday" in out_flag:
            if abs_up_trend > 0:
                out_flag = ""
                down_dzone = 0

        # 深度优化场景1：deep_ops1，这里要记录一个差值，如果差值过大，则停留3根，这差值的含义是刺穿回补
        # if 'Y' in color_lst[i - 1] and 'Y' in color_lst[i]:
        #     if abs(macd_lst[i]) / abs(macd_lst[i - 1]) > 1.18:
        #         deep_ops1 = False

        # 深度优化场景2：我们认为abs超过一半一直在一个在一个方向，则趋势失效
        # 刷新反方向的base, 上行已完成震荡了，上行趋势失效了，刷新反方向down_base
        if abs_up_trend > window_length * 0.6:
            up_trend = 0
            down_base = 0
        #  下行已完成震荡了，下行趋势失效了，刷新反方向base
        elif abs_down_trend > window_length * 0.6:
            down_trend = 0
            up_base = 0
        # upbase 刷新 base的选取还是要大于一个相对值的
        # up 情景分析
        # print(" down_trend_level and trend: ", down_trend_level, down_trend)
        if 'U' in color_lst[i] and 'Y' in color_lst[i] and macd_lst[i] > up_base * 1.1 and abs(macd_lst[i]) > \
                env[crypto]['price'] * env[crypto]['bal']:
            last_up_base = up_base
            up_base = macd_lst[i]
            sec_up_base = 0
            up_counter = 0
            down_trend = 0
            down_trend_level = 0
            print("upbase refresh")
        # down 情况分析
        # print("debuging down_base: ", down_base,downmax) if debug else debug
        if 'D' in color_lst[i] and 'Y' in color_lst[i] and abs(macd_lst[i]) > abs(down_base) * 1.1 and abs(
                macd_lst[i]) > env[crypto]['price'] * env[crypto]['bal']:
            last_down_base = down_base
            down_base = macd_lst[i]
            sec_down_base = 0
            down_counter = 0
            up_trend = 0
            up_trend_level = 0
        # 10倍 刺穿回补计算
        # Log(abs_down_trend,abs(macd_lst[i-1]),abs(macd_lst[i]),abs(macd_lst[i-1]) / abs(macd_lst[i]))
        if abs_up_trend > 0 and macd_lst[i - 1] / macd_lst[i] > 1.5:
            up_break_hole = 0
        if abs_down_trend > 0 and abs(macd_lst[i - 1]) / abs(macd_lst[i]) > 1.5:
            down_break_hole = 0
        down_counter += 1
        up_counter += 1
        # print("sec_up_base", sec_up_base)

        # print("down_trend",down_trend)

        # 绝对上下行让trend减弱，因为已经产生选择
        if abs_down_trend > 0 and up_counter > 10:
            down_trend += 1
        if abs_up_trend > 0 and down_counter > 10:
            up_trend += 1

    print("before:d/u", down_dzone, up_dzone)

    # 运行在上行，准备攻下：检查
    # 金叉后三根且经过了足够的上行震荡，准备进入下行，利用up_counter大于12 排除短震荡
    if abs_down_trend > 0 and abs_down_trend < 4 and up_counter > 12:
        down_factor += 3
        down_dzone = 1
        down_trend_level += 1
        print("马上暴跌")
    # 连续震荡超过基数，且无第一次减弱
    if up_counter > 14 and sec_up_base == 0:
        down_factor += 0.5
    # # 发生过选择,穿过就没了
    if down_trend > 0 and down_trend < 14 and down_trend == 0:
        down_factor = 1.5 + down_trend * 0.1
        down_dzone = 1
        print("有下行趋势，在距离范围内，是否判断对端无势力？")
    # # 最近一次发生过，且对势力无威胁
    if down_trend_level >= 1 and up_trend == 0 and down_trend < 14:
        down_dzone = 1
        down_factor = 1.5
        print("有下行趋势，发生过一次选择，是否且对端无势力？")
    # 长周期震荡发生选择
    if up_counter > 14 and down_trend > 0 and down_trend < 14:
        down_factor = 1.8 + down_trend * 0.1
        down_dzone = 1
        print("长震荡看下行")
    # 运行在下行，准备攻上：检查

    # 金叉后三根且无base压制
    if abs_up_trend > 0 and abs_up_trend < 4 and down_counter > 12:
        up_factor = 3
        up_dzone = 1
        print("下行金叉，马上暴涨")
        up_trend_level += 1
    # 连续震荡超过基数，且无第一次减弱
    if down_counter > 14 and sec_down_base == 0:
        up_factor = 0.5
    # 发生过选择
    if up_trend > 0 and up_trend < 14 and down_trend == 0:
        up_factor = 1.5 + up_trend * 0.1
        up_dzone = 1
        print("上行趋势，在距离范围内，是否判断对端无势力？")
    if up_trend_level >= 1 and down_trend == 0 and up_trend < 14:
        up_dzone = 1  # 持续危险
        up_factor = 1.5
        print("有上趋势，发生过一次选择，是否且对端无势力？")
    # 长周期震荡发生选择, 可能成功，如果成功，会进行猛涨，不能做空，做空请校验up_dzone
    if up_counter > 14 and up_trend > 0 and up_trend < 14:
        up_factor = 1.8 + up_counter * 0.1
        up_dzone = 1
        print("长震荡看上")

    # upbase放量重置
    if up_base == macd_lst[-1]:
        if last_up_base != 0:
            if up_base / last_up_base > 1.1:
                up_dzone = 1
                up_factor += 3
    if down_base == macd_lst[-1]:
        if last_down_base != 0:
            if abs(down_base) / abs(last_down_base) > 1.1:
                down_dzone = 1
                down_factor += 3

    # Todo 长周期+trend+second，最强趋势

    # TODO 背离
    # 如果在金叉形成后，价格没有上去，那么这个能力会进行挤压，挤压的能力会直接打破原来的局面

    # 发生过 may day 情况, 已经创建了危险区，已经重置了趋势就够了，主要是他不一定延续

    # 上下相互抵消
    if (down_dzone > 0 and up_dzone > 0) or (up_trend_level == down_trend_level and down_trend_level > 0):
        print("dzone rebalance, mode: ", dzone_mode)
        if dzone_mode == 'hard':
            print("before d/u level", down_trend_level, up_trend_level)
            if up_trend_level > down_trend_level:
                up_dzone = 1
                down_dzone = 0
            elif up_trend_level == down_trend_level:
                if up_trend > 0 and down_trend > 0:
                    if up_trend > down_trend:
                        down_dzone = 1
                        up_dzone = 0
                    else:
                        down_dzone = 0
                        up_dzone = 1
                else:
                    down_dzone = 0  # 无法分出胜负，因为趋势可能在增长
                    up_dzone = 0
            elif up_trend_level < down_trend_level:
                up_dzone = 0
                down_dzone = 1
            else:
                down_dzone = 0
                up_dzone = 0
        elif dzone_mode == "soft":
            down_dzone = 0
            up_dzone = 0
    print("after d/u", down_dzone, up_dzone)
    macd_info['down_counter'] = down_counter
    macd_info['up_counter'] = up_counter
    macd_info['up_base'] = up_base
    macd_info['down_base'] = down_base
    # macd_info['color_lst'] = color_lst
    macd_info['deep_ops1'] = deep_ops1
    macd_info['down_break_hole'] = down_break_hole
    macd_info['up_break_hole'] = up_break_hole
    macd_info['raw_data'] = macd_lst
    macd_info['down_trend'] = down_trend
    macd_info['up_trend'] = up_trend
    macd_info['price_high'] = record[-1]['High']
    macd_info['price_low'] = record[-1]['Low']
    macd_info['abs_down_trend'] = abs_down_trend
    macd_info['abs_up_trend'] = abs_up_trend
    macd_info['up_factor'] = up_factor
    macd_info['down_factor'] = down_factor
    macd_info['up_dzone'] = up_dzone
    macd_info['down_dzone'] = down_dzone
    macd_info['up_trend_level'] = up_trend_level
    macd_info['down_trend_level'] = down_trend_level
    macd_info['sec_up_base'] = sec_up_base
    macd_info['sec_down_base'] = sec_down_base
    macd_info['mayday'] = out_flag
    return macd_info


def env_analysis(crypto, exchange):
    # 0910 大小级别，环境变量危险区域特性优化
    kdj, macd, vegas = False, True, False
    ticker = _C(exchange.GetTicker)
    env[crypto]['price'] = ticker.Last
    if macd:
        #  初始化
        if "5m_up_dzoneC" not in env[crypto]:
            env[crypto]["5m_up_dzoneC"] = 0
        if "5m_down_dzoneC" not in env[crypto]:
            env[crypto]["5m_down_dzoneC"] = 0

        if "1h_up_dzoneC" not in env[crypto]:
            env[crypto]["1h_up_dzoneC"] = 0
        if "1h_down_dzoneC" not in env[crypto]:
            env[crypto]["1h_down_dzoneC"] = 0

        record_1h = exchange.GetRecords(PERIOD_H1)
        record_4h = exchange.GetRecords(PERIOD_H4)
        atr_list = TA.ATR(record_4h, 14)
        avg = sum(atr_list) / len(atr_list)
        #env[crypto]['atr_factor'] = 0.5 * avg / env[crypto]['price']
        # Log(env[crypto]['atr_factor'])
        #env[crypto]['atr_factor'] = 0.015
        # record_1d = exchange.GetRecords(PERIOD_D1)

        macd_1h_info = get_macd_log(crypto, record_1h)
        # Log(macd_1h_info)
        # 对于4h日志，最后一根没有收线，不纳入数据统计
        macd_4h_info = get_macd_log(crypto, record=record_4h, mode="lazy", dzone_mode="soft")
        # Log(macd_4h_info)
        # macd_1d_info = get_macd_log(record_1d)
        # Log(macd_1d_info)
        # debug
        # Log("env macd_4h_info  ", crypto, macd_4h_info)

        # factor 分析
        macd_up_factor = macd_1h_info['up_factor'] * 0.2 + macd_4h_info[
            'up_factor'] * 0.4  # + macd_1d_info['up_factor'] * 0.4
        macd_down_factor = macd_1h_info['down_factor'] * 0.2 + macd_4h_info[
            'down_factor'] * 0.4  # + macd_1d_info['down_factor'] * 0.4

        # 危险期简单传递，因为以后要进行复合分析，所以在这里整合
        env[crypto]['5m_up_dzone'], env[crypto]['5m_down_dzone'] = 0, 0
        env[crypto]['trend_factor'] = macd_up_factor - macd_down_factor
        env[crypto]['1h_up_dzone'] = macd_1h_info['up_dzone']
        env[crypto]['1h_down_dzone'] = macd_1h_info['down_dzone']
        env[crypto]['4h_up_dzone'] = macd_4h_info['up_dzone']
        env[crypto]['4h_down_dzone'] = macd_4h_info['down_dzone']
        # env_info['1d_up_dzone'] = macd_1d_info['up_dzone']
        # env_info['1d_down_dzone'] = macd_1d_info['down_dzone']

        setting_dzone("5m", crypto, "up", env[crypto]['5m_up_dzone'])
        setting_dzone("5m", crypto, "down", env[crypto]['5m_down_dzone'])

        setting_dzone("1h", crypto, "up", env[crypto]['1h_up_dzone'])  # 1h危险区生效
        setting_dzone("1h", crypto, "down", env[crypto]['1h_down_dzone'])

        setting_dzone("4h", crypto, "up", env[crypto]['4h_up_dzone'])
        setting_dzone("4h", crypto, "down", env[crypto]['4h_down_dzone'])
        # setting_dzone("1d", crypto, "long", env_info['1d_up_dzone'])
        # setting_dzone("1d", crypto, "long", env_info['1d_down_dzone'])
        # return env_info

        # 做单频率分析
        # if env[crypto]['1h_counter'] % 168 == 0:
        #     trading_info['maker_vol'] = 0


def refresh_info_table():
    account = _C(exchange.GetAccount)
    # 交易基本情况
    total = trading_info['lose_counter'] + trading_info['win_counter'] + trading_info['win_half_counter']
    if total > 0:
        trading_info['win_Rate'] = _N((trading_info['win_counter'] + trading_info['win_half_counter']) / total, 3)


    position = _C(exchange.GetPosition)
    if len(position) > 0:
        for i in range(0, len(position)):
            trading_info['f_balance'] += position[i].Margin
    row1 = []
    row1.append(trading_info['total_profit'])
    row1.append(trading_info['balance'])
    row1.append(trading_info['f_balance'])
    row1.append(trading_info['win_counter'])
    row1.append(trading_info['win_half_counter'])
    row1.append(trading_info['lose_counter'])
    row1.append(trading_info['win_Rate'])
    row1.append(trading_info['maker_vol']) # 做单频率,每周做单

    trading_info['lose_rate_list'] = [str(x)[:5] for x in trading_info['lose_rate_list']]
    trading_info['lose_rate']  = ' '.join(trading_info['lose_rate_list'])
    row1.append(trading_info['lose_rate'])

    table = {"type": "table", "title": "交易信息", "cols": ["总利润", "余额", "保证金", "完全止盈", "部分止盈", "亏损的单", "胜率","做单量", "亏损数组(调试)"],
             "rows": [row1]}

    LogStatus('`' + json.dumps(table) + '`')
    trading_info['f_balance'] = 0


PERIOD_H4 = 14400
env = {'sleeptime': 200}
trading_info = {"total_profit": 0, "balance": None, "f_balance": 0, "win_counter": 0, "lose_counter": 0, "win_Rate": 0,
                "win_half_counter": 0,"lose_rate_list": [], "maker_vol": 0}


def main():
    # exchange.IO("simulate", true)
    orderId_list_1h = []  # 为了记录对6种不同代币进行订单管理,平仓后记录收益差
    orderId_list_5m = {}
    args_list = []
    # 币种信息
    init_care_pair = ["ETH_USDT"]  # ["ETH_USDT","BTC_USDT","AVAX_USDT","MATIC_USDT","FTM_USDT","NEAR_USDT"]
    precision_dic = {"AVAX_USDT": 0, "NEAR_USDT": 0, "BTC_USDT": 3, "ETH_USDT": 3, "FTM_USDT": 0}
    profit_dic = {"AVAX_USDT": 0.016, "NEAR_USDT": 0.017,"FTM_USDT": 0.016, "BTC_USDT": 0.013, "ETH_USDT": 0.015}
    bal_dic = {"AVAX_USDT": 0.0008, "NEAR_USDT": 0.0008,"FTM_USDT": 0.0008, "BTC_USDT": 0.0006, "ETH_USDT": 0.0008}

    # 永续合约
    # exchange.IO("currency", "BTC_USDT")
    exchange.SetContractType("swap")

    # 连接交易所信息
    Log("连接交易所成功！", exchange.GetAccount())
    Log("读取变量成功！", window_length)
    record = _C(exchange.GetRecords, PERIOD_M5)
    Log("获取交易所日志", record)
    Log("计算MACD日志", TA.MACD(record, 13, 34, 9))
    Log("解析MACD日志", get_macd(record))

    # 初始化信息
    macd_info = {}
    for crypto in init_care_pair:
        macd_info[crypto] = {'5m': {}, "1h": {}}
        env[crypto] = {}

    Log("开始校验 货币信息")
    care_pair = []

    # 周期采样计数器

    for crypto in init_care_pair:
        exchange.IO("currency", crypto)
        record = _C(exchange.GetRecords, PERIOD_M5)
        Sleep(env['sleeptime'])
        if len(record) == 0:
            Log(crypto, "获取货币错误，请查看相关配置")
        else:

            # 环境读取
            env[crypto]['1h_counter'] = 0
            env[crypto]['5m_counter'] = 0
            env[crypto]['preci'] = precision_dic[crypto]
            env[crypto]['atr_factor'] = profit_dic[crypto]
            env[crypto]['bal'] = bal_dic[crypto]
            env_analysis(crypto, exchange)
            orderId_list_5m[crypto] = []
            macd_info[crypto]['1h'] = get_macd_log(crypto, record)
            macd_info[crypto]['5m'] = get_macd_log(crypto, record)
            Log("init 1h", crypto, macd_info[crypto]['1h'])
            Log("init 5m", crypto, macd_info[crypto]['5m'])
            macd_info[crypto]['1h_sample'] = []
            macd_info[crypto]['5m_sample'] = []
            macd_info[crypto]['1h_data_fresh'] = False
            macd_info[crypto]['5m_data_fresh'] = False
            macd_info[crypto]['1h_counter'] = 0
            macd_info[crypto]['5m_counter'] = 0

            macd_info[crypto]['1h_counter'], macd_info[crypto]['1h_up_target_counter'], macd_info[crypto][
                '1h_down_target_counter'] = 0, 0, 0
            macd_info[crypto]['5m_counter'], macd_info[crypto]['5m_up_target_counter'], macd_info[crypto][
                '5m_down_target_counter'] = 0, 0, 0

            # 0910 大小级别，危险区域特性优化
            macd_info[crypto]['1h_up_dzone'], macd_info[crypto]['1h_down_dzone'] = 0, 0
            macd_info[crypto]['5m_up_dzone'], macd_info[crypto]['5m_down_dzone'] = 0, 0

            care_pair.append(crypto)
    Log("完成所有检查项，开始启动分析:", care_pair)
    margin_every = 0

    while True:
        for crypto in care_pair:
            account = _C(exchange.GetAccount)
            
            bal_use = float(account.Info.data[0].get("details")[0].get("availEq"))* 1.35
            trading_info['balance'] = float(account.Info.data[0].get("details")[0].get("availEq"))
            # bal_use = account.Balance * 0.1
            # trading_info['balance'] = account.Balance
            margin_every = bal_use
            # if bal_use > 100:
            #     margin_every = bal_use
            # Log(margin_every)
            # Log(record)
            # Log(get_macd(record))
            # 初始化时间延迟，防止重复下单
            exchange.IO("currency", crypto)
            # exchange.SetMaxBarLen(80)
            timestamp = time.time()
            dt = datetime.datetime.fromtimestamp(timestamp)

            # 5min 采样
            time_slot_1h = str(dt.year) + str(dt.month) + str(dt.day) + str(dt.hour)
            time_slot_5m = str(dt.year) + str(dt.month) + str(dt.day) + str(dt.hour) + str(dt.minute / 5) + str(
                dt.minute % 5)

            if dt.minute == 0 and (time_slot_1h not in macd_info[crypto]['1h_sample']):
                r = exchange.GetRecords(PERIOD_H1)
                macd_info[crypto]['1h_sample'].append(time_slot_1h)
                # 采集日志
                macd_info[crypto]['1h'] = get_macd_log(crypto, r)
                # Log("1h refresh report", crypto, macd_info[crypto]['1h_counter'], macd_info[crypto]['1h'])
                # 环境分析每小时执行1次，目的是刷新env,env包含危险区信息
                # 回收

                if len(macd_info[crypto]['1h_sample']) > 300:
                    macd_info[crypto]['1h_sample'] = macd_info[crypto]['1h_sample'][-300:]
                macd_info[crypto]['1h_counter'] += 1
                env[crypto]['1h_counter'] += 1
                env_analysis(crypto, exchange)
                # Log("env", crypto, env[crypto])

            # 1h 采样调试
            # Log("1h_sampple", macd_info['1h_sample'])

            # counter步长调试
            # Log("counter: now / up / down ", macd_info[crypto]['1h_counter'],macd_info[crypto]['1h_up_target_counter'],macd_info[crypto]['1h_down_target_counter'])

            if dt.minute % 5 == 0 and (time_slot_5m not in macd_info[crypto]['5m_sample']):
                r = exchange.GetRecords(PERIOD_M5)
                macd_info[crypto]['5m'] = get_macd_log(crypto, r)
                macd_info[crypto]['5m_sample'].append(time_slot_5m)
                # Log("5min refresh report", crypto, macd_info[crypto]['5m_counter'], macd_info[crypto]['5m'])

                # 回收
                if len(macd_info[crypto]['5m_sample']) > 300:
                    macd_info[crypto]['5m_sample'] = macd_info[crypto]['5m_sample'][-300:]
                macd_info[crypto]['5m_counter'] += 1
                env[crypto]['5m_counter'] += 1

                # Log("before scan_log crypto:",crypto, macd_info)
                # if macd_info[crypto]['1h']:
                #     macd_info[crypto]['cur_1h'] = get_macd(exchange.GetRecords(PERIOD_H1))[-1]
                #     arg_1h, macd_info[crypto]['1h'] = scan_log(crypto, macd_info[crypto], "1h")
                #     if arg_1h['dirct'] != None:
                #         o = onTick(arg_1h,"1h")
                #         orderId_list_1h.append(o)

                # if len(orderId_list_1h) > 0:
                #     (index1,index2) =  harvest_profits2(orderId_list_1h)
                #     if len(index1) > 0:
                #         for i in range (0,len(index1)):
                #             orderId_list_1h.remove(index1[i])

                #     if len(index2) > 0:
                #         for i in range (0,len(index2)):
                #             orderId_list_1h.remove(index2[i])

                #     (index3,index4) =  stop_lose(orderId_list_1h)

                #     if len(index3) > 0:
                #         for i in range (0,len(index3)):
                #             orderId_list_1h.remove(index3[i])

                #     if len(index4) > 0:
                #         for i in range (0,len(index4)):
                #             orderId_list_1h.remove(index4[i])
                #     args_list.append(arg_1h)


                # Log("cur_5m",macd_info[crypto]['cur_5m'])
                macd_info[crypto]['cur_5m'] = get_macd(exchange.GetRecords(PERIOD_M5))[-1]
                arg_5m, macd_info[crypto]['5m'] = scan_log(crypto, macd_info[crypto], "5m")
                if arg_5m['dirct'] != None:
                    o = onTick(arg_5m, "5m", margin_every)
                    orderId_list_5m[crypto].append(o)

                if len(orderId_list_5m[crypto]) > 0:

                    # 移动止损价格变化
                    move_harvest_profits2(orderId_list_5m[crypto], margin_every)

                    # 留2手+相对止损
                    stop_lose(orderId_list_5m[crypto])

                    # 执行止损
                orderId_list_5m[crypto] = [x for x in orderId_list_5m[crypto] if x.out == False]

            # 加一次观测，多一次机会；只是多一次机会
            if dt.minute % 5 == 0 and dt.second == 30:
                if macd_info[crypto]['5m']:
                    macd_info[crypto]['cur_5m'] = get_macd(exchange.GetRecords(PERIOD_M5))[-1]
                    arg_5m, macd_info[crypto]['5m'] = scan_log(crypto, macd_info[crypto], "5m")
                    if arg_5m['dirct'] != None:
                        o = onTick(arg_5m, "5m", margin_every)
                        orderId_list_5m[crypto].append(o)

            # 止盈实时执行
            if len(orderId_list_5m) > 0:
                (index1, index2) = harvest_profits2(orderId_list_5m[crypto], margin_every)
                if len(index1) > 0:
                    for i in range(0, len(index1)):
                        if index1[i] in index1:
                            orderId_list_5m[crypto].remove(index1[i])

                if len(index2) > 0:
                    for i in range(0, len(index2)):
                        if index2[i] in index2:
                            orderId_list_5m[crypto].remove(index2[i])

            # 绝对止损
            # abs_stop_lose(orderId_list_5m[crypto])

            # 更新状态信息
            refresh_info_table()
            Sleep(350)
