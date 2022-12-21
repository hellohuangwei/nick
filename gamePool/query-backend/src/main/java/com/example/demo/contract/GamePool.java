package com.example.demo.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.4.
 */
@SuppressWarnings("rawtypes")
public class GamePool extends Contract {
    public static final String BINARY = "60806040526005805460a060020a60ff021916740100000000000000000000000000000000000000001790553480156200003857600080fd5b506040516200242b3803806200242b8339810160408190526200005b91620000ff565b6200006e640100000000620000de810204565b60028054600160a060020a0319908116600160a060020a03938416179091556003805482169783169790971790965560008054871695821695909517909455600180548616938516939093179092556004805485169184169190911790556005805490931691161790556200016f565b3390565b8051600160a060020a0381168114620000fa57600080fd5b919050565b600080600080600060a086880312156200011857600080fd5b6200012386620000e2565b94506200013360208701620000e2565b93506200014360408701620000e2565b92506200015360608701620000e2565b91506200016360808701620000e2565b90509295509295909350565b6122ac806200017f6000396000f3fe608060405234801561001057600080fd5b50600436106100d35760003560e060020a9004806353dbd62111610090578063ac9650d81161006a578063ac9650d8146101d7578063bc197c81146101f7578063f23a6e611461022f578063f45418471461026757600080fd5b806353dbd6211461019e5780636f88c599146101b15780637e5c1630146101c457600080fd5b806301ffc9a7146100d857806311d47ee414610100578063150b7a021461011557806315c378dc146101655780632328737e146101785780634c8345161461018b575b600080fd5b6100eb6100e6366004611a23565b61027a565b60405190151581526020015b60405180910390f35b61011361010e366004611afa565b6102e3565b005b61014c610123366004611b66565b7f150b7a0200000000000000000000000000000000000000000000000000000000949350505050565b604051600160e060020a031990911681526020016100f7565b610113610173366004611bce565b6105c6565b610113610186366004611bf8565b6106dd565b610113610199366004611c90565b6107b3565b6101136101ac366004611bf8565b6108bc565b6101136101bf366004611afa565b61094b565b6101136101d2366004611d04565b610bc7565b6101ea6101e5366004611d26565b610ca5565b6040516100f79190611df6565b61014c610205366004611e57565b7fbc197c810000000000000000000000000000000000000000000000000000000095945050505050565b61014c61023d366004611f01565b7ff23a6e610000000000000000000000000000000000000000000000000000000095945050505050565b610113610275366004611bf8565b610d9a565b6000600160e060020a031982167f4e2312e00000000000000000000000000000000000000000000000000000000014806102dd57507f01ffc9a700000000000000000000000000000000000000000000000000000000600160e060020a03198316145b92915050565b60055474010000000000000000000000000000000000000000900460ff166103555760405160e560020a62461bcd02815260206004820152600a60248201527f72652d656e74657265640000000000000000000000000000000000000000000060448201526064015b60405180910390fd5b6005805474ff000000000000000000000000000000000000000019169055604051600690610384908490611f66565b9081526020016040518091039020546000146103e55760405160e560020a62461bcd02815260206004820152601160248201527f50726f6f66206861732065787069726564000000000000000000000000000000604482015260640161034c565b60035460408051808201909152600a81527f45524332305f434f494e00000000000000000000000000000000000000000000602082015261043591600160a060020a031690859085908590610e49565b15156001146104895760405160e560020a62461bcd02815260206004820152601d60248201527f596f7520646f6e277420676574207468652070726f6f66207269676874000000604482015260640161034c565b600554600160a060020a031663a9059cbb3360405160e060020a63ffffffff8416028152600160a060020a03909116600482015260248101869052604401602060405180830381600087803b1580156104e157600080fd5b505af11580156104f5573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105199190611f82565b50600160068360405161052c9190611f66565b908152604051908190036020019020557f61a3e96d3431463afef154bea4a6ec246824115c5bf2d2bb8cb010334253f1aa6105643390565b60408051600160a060020a03909216825260208201869052810183905260600160405180910390a150506005805474ff000000000000000000000000000000000000000019167401000000000000000000000000000000000000000017905550565b600254600160a060020a0316331461060c576040517f8e4a23d600000000000000000000000000000000000000000000000000000000815233600482015260240161034c565b6001546040517f42842e0e000000000000000000000000000000000000000000000000000000008152600160a060020a03909116906342842e0e9061065990309086908690600401611fa4565b600060405180830381600087803b15801561067357600080fd5b505af1158015610687573d6000803e3d6000fd5b505060408051600160a060020a03861681526020810185905242918101919091527f327454c5522764f3d4b979585dd490a20ad6fb44a4ab26f9a8591ec8416d225d925060600190505b60405180910390a15050565b600554600160a060020a03166323b872dd3330846040518463ffffffff1660e060020a02815260040161071293929190611fa4565b602060405180830381600087803b15801561072c57600080fd5b505af1158015610740573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107649190611f82565b507fd584b5281a7800efcafa65c751a435c33333fdb5f0e83c6990e958d2274e92a3335b60408051600160a060020a03909216825260208201849052429082015260600160405180910390a150565b600254600160a060020a031633146107f9576040517f8e4a23d600000000000000000000000000000000000000000000000000000000815233600482015260240161034c565b6000546040517f2eb2c2d6000000000000000000000000000000000000000000000000000000008152600160a060020a0390911690632eb2c2d690610848903090879087908790600401612003565b600060405180830381600087803b15801561086257600080fd5b505af1158015610876573d6000803e3d6000fd5b505050507fa2b9b8f4d3ae99f2c9e3c2d0063488cc3a940445a9313f2acb419148663c9361838383426040516108af9493929190612084565b60405180910390a1505050565b600154600160a060020a03166342842e0e3330846040518463ffffffff1660e060020a0281526004016108f193929190611fa4565b600060405180830381600087803b15801561090b57600080fd5b505af115801561091f573d6000803e3d6000fd5b505050507fabf47425ddb3f5e36a15479a5ff4f3a2fa1e95743eb2eabf3115173ba47341d96107883390565b60055474010000000000000000000000000000000000000000900460ff166109b85760405160e560020a62461bcd02815260206004820152600a60248201527f72652d656e746572656400000000000000000000000000000000000000000000604482015260640161034c565b6005805474ff0000000000000000000000000000000000000000191690556040516006906109e7908490611f66565b908152602001604051809103902054600014610a485760405160e560020a62461bcd02815260206004820152601160248201527f50726f6f66206861732065787069726564000000000000000000000000000000604482015260640161034c565b60035460408051808201909152600981527f45524332305f43595400000000000000000000000000000000000000000000006020820152610a9891600160a060020a031690859085908590610e49565b1515600114610aec5760405160e560020a62461bcd02815260206004820152601d60248201527f596f7520646f6e277420676574207468652070726f6f66207269676874000000604482015260640161034c565b600454600160a060020a031663a9059cbb3360405160e060020a63ffffffff8416028152600160a060020a03909116600482015260248101869052604401602060405180830381600087803b158015610b4457600080fd5b505af1158015610b58573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610b7c9190611f82565b506001600683604051610b8f9190611f66565b908152604051908190036020019020557f7f98b3c778eb31eb6d3481b9d2382f77ebdd6dff08d11f7461a37c6bd46715746105643390565b600054600160a060020a031663f242432a3360405160e060020a63ffffffff8416028152600160a060020a039091166004820152306024820152604481018590526064810184905260a06084820152600060a482015260c401600060405180830381600087803b158015610c3a57600080fd5b505af1158015610c4e573d6000803e3d6000fd5b505050507f25c856fb93ae2f6e1a36e4523ea53aa08122d397adc44b91171b05e2bb69f826610c7a3390565b60408051600160a060020a0390921682526020820185905281018390524260608201526080016106d1565b60608167ffffffffffffffff811115610cc057610cc0611a40565b604051908082528060200260200182016040528015610cf357816020015b6060815260200190600190039081610cde5790505b50905060005b82811015610d9357610d6330858584818110610d1757610d176120ca565b9050602002810190610d2991906120e3565b8080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250610e7b92505050565b828281518110610d7557610d756120ca565b60200260200101819052508080610d8b90612143565b915050610cf9565b5092915050565b600454600160a060020a03166323b872dd3330846040518463ffffffff1660e060020a028152600401610dcf93929190611fa4565b602060405180830381600087803b158015610de957600080fd5b505af1158015610dfd573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610e219190611f82565b507fedd102637418c4ef2ed0731943da4091ad70ec192359cbfed690d267ffb58c7d33610788565b6000610e7186610e6b33610e5c89610ea7565b610e6588610ea7565b87610fcc565b8661105e565b9695505050505050565b6060610ea08383604051806060016040528060278152602001612250602791396111db565b9392505050565b606081610ee757505060408051808201909152600181527f3000000000000000000000000000000000000000000000000000000000000000602082015290565b8160005b8115610f115780610efb81612143565b9150610f0a9050600a83612177565b9150610eeb565b60008167ffffffffffffffff811115610f2c57610f2c611a40565b6040519080825280601f01601f191660200182016040528015610f56576020820181803683370190505b5090505b8415610fc457610f6b60018361218b565b9150610f78600a866121a2565b610f839060306121b6565b60f860020a02818381518110610f9b57610f9b6120ca565b6020010190600160f860020a031916908160001a905350610fbd600a86612177565b9450610f5a565b949350505050565b6000611055610fe5610fdd876112c8565b868686611307565b604051602001610ff59190611f66565b60408051601f1981840301815282825280516020918201207f19457468657265756d205369676e6564204d6573736167653a0a33320000000084830152603c8085019190915282518085039091018152605c909301909152815191012090565b95945050505050565b600080600061106d8585611576565b90925090506000816004811115611086576110866121ce565b1480156110a4575085600160a060020a031682600160a060020a0316145b156110b457600192505050610ea0565b60008087600160a060020a0316631626ba7e60e060020a0288886040516024016110df9291906121e7565b60408051601f198184030181529181526020820180517bffffffffffffffffffffffffffffffffffffffffffffffffffffffff16600160e060020a03199094169390931790925290516111329190611f66565b600060405180830381855afa9150503d806000811461116d576040519150601f19603f3d011682016040523d82523d6000602084013e611172565b606091505b5091509150818015611185575080516020145b80156111cf575080517f1626ba7e00000000000000000000000000000000000000000000000000000000906111c39083016020908101908401612200565b600160e060020a031916145b98975050505050505050565b6060600160a060020a0384163b61125d5760405160e560020a62461bcd02815260206004820152602660248201527f416464726573733a2064656c65676174652063616c6c20746f206e6f6e2d636f60448201527f6e74726163740000000000000000000000000000000000000000000000000000606482015260840161034c565b60008085600160a060020a0316856040516112789190611f66565b600060405180830381855af49150503d80600081146112b3576040519150601f19603f3d011682016040523d82523d6000602084013e6112b8565b606091505b5091509150610e718282866115e6565b6040516c01000000000000000000000000600160a060020a0383160260208201526060906102dd90603401604051602081830303815290604052611622565b805182518451865160609388938893889388936000939092909161132b91906121b6565b61133591906121b6565b61133f91906121b6565b67ffffffffffffffff81111561135757611357611a40565b6040519080825280601f01601f191660200182016040528015611381576020820181803683370190505b509050806000805b87518110156113fd578781815181106113a4576113a46120ca565b602001015160f860020a900460f860020a028383806113c290612143565b9450815181106113d4576113d46120ca565b6020010190600160f860020a031916908160001a905350806113f581612143565b915050611389565b5060005b86518110156114755786818151811061141c5761141c6120ca565b602001015160f860020a900460f860020a0283838061143a90612143565b94508151811061144c5761144c6120ca565b6020010190600160f860020a031916908160001a9053508061146d81612143565b915050611401565b5060005b85518110156114ed57858181518110611494576114946120ca565b602001015160f860020a900460f860020a028383806114b290612143565b9450815181106114c4576114c46120ca565b6020010190600160f860020a031916908160001a905350806114e581612143565b915050611479565b5060005b84518110156115655784818151811061150c5761150c6120ca565b602001015160f860020a900460f860020a0283838061152a90612143565b94508151811061153c5761153c6120ca565b6020010190600160f860020a031916908160001a9053508061155d81612143565b9150506114f1565b50919b9a5050505050505050505050565b6000808251604114156115ad5760208301516040840151606085015160001a6115a1878285856118ad565b945094505050506115df565b8251604014156115d757602083015160408401516115cc86838361199a565b9350935050506115df565b506000905060025b9250929050565b606083156115f5575081610ea0565b8251156116055782518084602001fd5b8160405160e560020a62461bcd02815260040161034c919061221d565b60408051808201909152601081527f3031323334353637383961626364656600000000000000000000000000000000602082015281516060919060009061166a906002612230565b6116759060026121b6565b67ffffffffffffffff81111561168d5761168d611a40565b6040519080825280601f01601f1916602001820160405280156116b7576020820181803683370190505b5090507f3000000000000000000000000000000000000000000000000000000000000000816000815181106116ee576116ee6120ca565b6020010190600160f860020a031916908160001a9053507f780000000000000000000000000000000000000000000000000000000000000081600181518110611739576117396120ca565b6020010190600160f860020a031916908160001a90535060005b84518110156118a557826004868381518110611771576117716120ca565b602001015160f860020a900460f860020a02600160f860020a031916908060020a820491505060f860020a900460ff16815181106117b1576117b16120ca565b016020015160f860020a9081900402826117cc836002612230565b6117d79060026121b6565b815181106117e7576117e76120ca565b6020010190600160f860020a031916908160001a90535082858281518110611811576118116120ca565b602001015160f860020a900460f860020a02600f60f860020a021660f860020a900460ff1681518110611846576118466120ca565b016020015160f860020a908190040282611861836002612230565b61186c9060036121b6565b8151811061187c5761187c6120ca565b6020010190600160f860020a031916908160001a9053508061189d81612143565b915050611753565b509392505050565b6000807f7fffffffffffffffffffffffffffffff5d576e7357a4501ddfe92f46681b20a08311156118e45750600090506003611991565b8460ff16601b141580156118fc57508460ff16601c14155b1561190d5750600090506004611991565b6040805160008082526020820180845289905260ff881692820192909252606081018690526080810185905260019060a0016020604051602081039080840390855afa158015611961573d6000803e3d6000fd5b5050604051601f190151915050600160a060020a03811661198a57600060019250925050611991565b9150600090505b94509492505050565b6000807f7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8316816119ee7f80000000000000000000000000000000000000000000000000000000000000008604601b6121b6565b90506119fc878288856118ad565b935093505050935093915050565b600160e060020a031981168114611a2057600080fd5b50565b600060208284031215611a3557600080fd5b8135610ea081611a0a565b60e060020a634e487b7102600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715611a8257611a82611a40565b604052919050565b600082601f830112611a9b57600080fd5b813567ffffffffffffffff811115611ab557611ab5611a40565b611ac8601f8201601f1916602001611a59565b818152846020838601011115611add57600080fd5b816020850160208301376000918101602001919091529392505050565b600080600060608486031215611b0f57600080fd5b83359250602084013567ffffffffffffffff811115611b2d57600080fd5b611b3986828701611a8a565b925050604084013590509250925092565b8035600160a060020a0381168114611b6157600080fd5b919050565b60008060008060808587031215611b7c57600080fd5b611b8585611b4a565b9350611b9360208601611b4a565b925060408501359150606085013567ffffffffffffffff811115611bb657600080fd5b611bc287828801611a8a565b91505092959194509250565b60008060408385031215611be157600080fd5b611bea83611b4a565b946020939093013593505050565b600060208284031215611c0a57600080fd5b5035919050565b600082601f830112611c2257600080fd5b8135602067ffffffffffffffff821115611c3e57611c3e611a40565b808202611c4c828201611a59565b9283528481018201928281019087851115611c6657600080fd5b83870192505b84831015611c8557823582529183019190830190611c6c565b979650505050505050565b600080600060608486031215611ca557600080fd5b611cae84611b4a565b9250602084013567ffffffffffffffff80821115611ccb57600080fd5b611cd787838801611c11565b93506040860135915080821115611ced57600080fd5b50611cfa86828701611c11565b9150509250925092565b60008060408385031215611d1757600080fd5b50508035926020909101359150565b60008060208385031215611d3957600080fd5b823567ffffffffffffffff80821115611d5157600080fd5b818501915085601f830112611d6557600080fd5b813581811115611d7457600080fd5b8660208083028501011115611d8857600080fd5b60209290920196919550909350505050565b60005b83811015611db5578181015183820152602001611d9d565b83811115611dc4576000848401525b50505050565b60008151808452611de2816020860160208601611d9a565b601f01601f19169290920160200192915050565b600060208083018184528085518083526040860191506040848202870101925083870160005b82811015611e4a57603f19888603018452611e38858351611dca565b94509285019290850190600101611e1c565b5092979650505050505050565b600080600080600060a08688031215611e6f57600080fd5b611e7886611b4a565b9450611e8660208701611b4a565b9350604086013567ffffffffffffffff80821115611ea357600080fd5b611eaf89838a01611c11565b94506060880135915080821115611ec557600080fd5b611ed189838a01611c11565b93506080880135915080821115611ee757600080fd5b50611ef488828901611a8a565b9150509295509295909350565b600080600080600060a08688031215611f1957600080fd5b611f2286611b4a565b9450611f3060208701611b4a565b93506040860135925060608601359150608086013567ffffffffffffffff811115611f5a57600080fd5b611ef488828901611a8a565b60008251611f78818460208701611d9a565b9190910192915050565b600060208284031215611f9457600080fd5b81518015158114610ea057600080fd5b600160a060020a039384168152919092166020820152604081019190915260600190565b600081518084526020808501945080840160005b83811015611ff857815187529582019590820190600101611fdc565b509495945050505050565b6000600160a060020a03808716835280861660208401525060a0604083015261202f60a0830185611fc8565b82810360608401526120418185611fc8565b8381036080909401939093525050600281527f30780000000000000000000000000000000000000000000000000000000000006020820152604001949350505050565b600160a060020a03851681526080602082015260006120a66080830186611fc8565b82810360408401526120b88186611fc8565b91505082606083015295945050505050565b60e060020a634e487b7102600052603260045260246000fd5b6000808335601e198436030181126120fa57600080fd5b83018035915067ffffffffffffffff82111561211557600080fd5b6020019150368190038213156115df57600080fd5b60e060020a634e487b7102600052601160045260246000fd5b60006000198214156121575761215761212a565b5060010190565b60e060020a634e487b7102600052601260045260246000fd5b6000826121865761218661215e565b500490565b60008282101561219d5761219d61212a565b500390565b6000826121b1576121b161215e565b500690565b600082198211156121c9576121c961212a565b500190565b60e060020a634e487b7102600052602160045260246000fd5b828152604060208201526000610fc46040830184611dca565b60006020828403121561221257600080fd5b8151610ea081611a0a565b602081526000610ea06020830184611dca565b600081600019048311821515161561224a5761224a61212a565b50029056fe416464726573733a206c6f772d6c6576656c2064656c65676174652063616c6c206661696c6564a26469706673582212206a168b3e8e3f9dccccf60d6fe6c8f62c86cf0a00093019a16be29b53a8faa1f764736f6c63430008090033";

    public static final String FUNC_LOADINGCOIN = "loadingCoin";

    public static final String FUNC_LOADINGCYT = "loadingCyt";

    public static final String FUNC_LOADINGGAMEPROPE = "loadingGamePrope";

    public static final String FUNC_LOADINGROLE = "loadingRole";

    public static final String FUNC_MULTICALL = "multicall";

    public static final String FUNC_ONERC1155BATCHRECEIVED = "onERC1155BatchReceived";

    public static final String FUNC_ONERC1155RECEIVED = "onERC1155Received";

    public static final String FUNC_ONERC721RECEIVED = "onERC721Received";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_WITHDRAWCOIN = "withdrawCoin";

    public static final String FUNC_WITHDRAWCYT = "withdrawCyt";

    public static final String FUNC_WITHDRAWGAMEPROBE = "withdrawGameProbe";

    public static final String FUNC_WITHDRAWROLE = "withdrawRole";

    public static final Event LOADINGCOINEVENT_EVENT = new Event("loadingCoinEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOADINGCYTEVENT_EVENT = new Event("loadingCytEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOADINGGAMEPROPEEVENT_EVENT = new Event("loadingGamePropeEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOADINGROLEEVENT_EVENT = new Event("loadingRoleEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWCOINEVENT_EVENT = new Event("withdrawCoinEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWCYTEVENT_EVENT = new Event("withdrawCytEVent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWGAMEPROEEVENT_EVENT = new Event("withdrawGameProeEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWROLEEVENT_EVENT = new Event("withdrawRoleEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected GamePool(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GamePool(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected GamePool(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected GamePool(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<LoadingCoinEventEventResponse> getLoadingCoinEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(LOADINGCOINEVENT_EVENT, transactionReceipt);
        ArrayList<LoadingCoinEventEventResponse> responses = new ArrayList<LoadingCoinEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            LoadingCoinEventEventResponse typedResponse = new LoadingCoinEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LoadingCoinEventEventResponse> loadingCoinEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LoadingCoinEventEventResponse>() {
            @Override
            public LoadingCoinEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(LOADINGCOINEVENT_EVENT, log);
                LoadingCoinEventEventResponse typedResponse = new LoadingCoinEventEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LoadingCoinEventEventResponse> loadingCoinEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOADINGCOINEVENT_EVENT));
        return loadingCoinEventEventFlowable(filter);
    }

    public List<LoadingCytEventEventResponse> getLoadingCytEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(LOADINGCYTEVENT_EVENT, transactionReceipt);
        ArrayList<LoadingCytEventEventResponse> responses = new ArrayList<LoadingCytEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            LoadingCytEventEventResponse typedResponse = new LoadingCytEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LoadingCytEventEventResponse> loadingCytEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LoadingCytEventEventResponse>() {
            @Override
            public LoadingCytEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(LOADINGCYTEVENT_EVENT, log);
                LoadingCytEventEventResponse typedResponse = new LoadingCytEventEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LoadingCytEventEventResponse> loadingCytEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOADINGCYTEVENT_EVENT));
        return loadingCytEventEventFlowable(filter);
    }

    public List<LoadingGamePropeEventEventResponse> getLoadingGamePropeEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(LOADINGGAMEPROPEEVENT_EVENT, transactionReceipt);
        ArrayList<LoadingGamePropeEventEventResponse> responses = new ArrayList<LoadingGamePropeEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            LoadingGamePropeEventEventResponse typedResponse = new LoadingGamePropeEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LoadingGamePropeEventEventResponse> loadingGamePropeEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LoadingGamePropeEventEventResponse>() {
            @Override
            public LoadingGamePropeEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(LOADINGGAMEPROPEEVENT_EVENT, log);
                LoadingGamePropeEventEventResponse typedResponse = new LoadingGamePropeEventEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LoadingGamePropeEventEventResponse> loadingGamePropeEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOADINGGAMEPROPEEVENT_EVENT));
        return loadingGamePropeEventEventFlowable(filter);
    }

    public List<LoadingRoleEventEventResponse> getLoadingRoleEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(LOADINGROLEEVENT_EVENT, transactionReceipt);
        ArrayList<LoadingRoleEventEventResponse> responses = new ArrayList<LoadingRoleEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            LoadingRoleEventEventResponse typedResponse = new LoadingRoleEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LoadingRoleEventEventResponse> loadingRoleEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LoadingRoleEventEventResponse>() {
            @Override
            public LoadingRoleEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(LOADINGROLEEVENT_EVENT, log);
                LoadingRoleEventEventResponse typedResponse = new LoadingRoleEventEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LoadingRoleEventEventResponse> loadingRoleEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOADINGROLEEVENT_EVENT));
        return loadingRoleEventEventFlowable(filter);
    }

    public List<WithdrawCoinEventEventResponse> getWithdrawCoinEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWCOINEVENT_EVENT, transactionReceipt);
        ArrayList<WithdrawCoinEventEventResponse> responses = new ArrayList<WithdrawCoinEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WithdrawCoinEventEventResponse typedResponse = new WithdrawCoinEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.player = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawCoinEventEventResponse> withdrawCoinEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WithdrawCoinEventEventResponse>() {
            @Override
            public WithdrawCoinEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWCOINEVENT_EVENT, log);
                WithdrawCoinEventEventResponse typedResponse = new WithdrawCoinEventEventResponse();
                typedResponse.log = log;
                typedResponse.player = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawCoinEventEventResponse> withdrawCoinEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWCOINEVENT_EVENT));
        return withdrawCoinEventEventFlowable(filter);
    }

    public List<WithdrawCytEVentEventResponse> getWithdrawCytEVentEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWCYTEVENT_EVENT, transactionReceipt);
        ArrayList<WithdrawCytEVentEventResponse> responses = new ArrayList<WithdrawCytEVentEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WithdrawCytEVentEventResponse typedResponse = new WithdrawCytEVentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.player = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawCytEVentEventResponse> withdrawCytEVentEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WithdrawCytEVentEventResponse>() {
            @Override
            public WithdrawCytEVentEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWCYTEVENT_EVENT, log);
                WithdrawCytEVentEventResponse typedResponse = new WithdrawCytEVentEventResponse();
                typedResponse.log = log;
                typedResponse.player = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawCytEVentEventResponse> withdrawCytEVentEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWCYTEVENT_EVENT));
        return withdrawCytEVentEventFlowable(filter);
    }

    public List<WithdrawGameProeEventEventResponse> getWithdrawGameProeEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWGAMEPROEEVENT_EVENT, transactionReceipt);
        ArrayList<WithdrawGameProeEventEventResponse> responses = new ArrayList<WithdrawGameProeEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WithdrawGameProeEventEventResponse typedResponse = new WithdrawGameProeEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.player = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.ids = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amounts = (List<BigInteger>) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawGameProeEventEventResponse> withdrawGameProeEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WithdrawGameProeEventEventResponse>() {
            @Override
            public WithdrawGameProeEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWGAMEPROEEVENT_EVENT, log);
                WithdrawGameProeEventEventResponse typedResponse = new WithdrawGameProeEventEventResponse();
                typedResponse.log = log;
                typedResponse.player = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.ids = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amounts = (List<BigInteger>) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawGameProeEventEventResponse> withdrawGameProeEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWGAMEPROEEVENT_EVENT));
        return withdrawGameProeEventEventFlowable(filter);
    }

    public List<WithdrawRoleEventEventResponse> getWithdrawRoleEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWROLEEVENT_EVENT, transactionReceipt);
        ArrayList<WithdrawRoleEventEventResponse> responses = new ArrayList<WithdrawRoleEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WithdrawRoleEventEventResponse typedResponse = new WithdrawRoleEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.player = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawRoleEventEventResponse> withdrawRoleEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WithdrawRoleEventEventResponse>() {
            @Override
            public WithdrawRoleEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWROLEEVENT_EVENT, log);
                WithdrawRoleEventEventResponse typedResponse = new WithdrawRoleEventEventResponse();
                typedResponse.log = log;
                typedResponse.player = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timeStamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawRoleEventEventResponse> withdrawRoleEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWROLEEVENT_EVENT));
        return withdrawRoleEventEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> loadingCoin(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOADINGCOIN, 
                Arrays.<Type>asList(new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> loadingCyt(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOADINGCYT, 
                Arrays.<Type>asList(new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> loadingGamePrope(BigInteger id, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOADINGGAMEPROPE, 
                Arrays.<Type>asList(new Uint256(id),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> loadingRole(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOADINGROLE, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> multicall(List<byte[]> data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MULTICALL, 
                Arrays.<Type>asList(new DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(data, org.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> onERC1155BatchReceived(String param0, String param1, List<BigInteger> param2, List<BigInteger> param3, byte[] param4) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ONERC1155BATCHRECEIVED, 
                Arrays.<Type>asList(new Address(160, param0),
                new Address(160, param1),
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(param2, Uint256.class)),
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(param3, Uint256.class)),
                new org.web3j.abi.datatypes.DynamicBytes(param4)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> onERC1155Received(String param0, String param1, BigInteger param2, BigInteger param3, byte[] param4) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ONERC1155RECEIVED, 
                Arrays.<Type>asList(new Address(160, param0),
                new Address(160, param1),
                new Uint256(param2),
                new Uint256(param3),
                new org.web3j.abi.datatypes.DynamicBytes(param4)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> onERC721Received(String param0, String param1, BigInteger param2, byte[] param3) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ONERC721RECEIVED, 
                Arrays.<Type>asList(new Address(160, param0),
                new Address(160, param1),
                new Uint256(param2),
                new org.web3j.abi.datatypes.DynamicBytes(param3)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawCoin(BigInteger amount, byte[] signature, BigInteger currentTimeStamp) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWCOIN, 
                Arrays.<Type>asList(new Uint256(amount),
                new org.web3j.abi.datatypes.DynamicBytes(signature), 
                new Uint256(currentTimeStamp)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawCyt(BigInteger amount, byte[] signature, BigInteger currentTimeStamp) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWCYT, 
                Arrays.<Type>asList(new Uint256(amount),
                new org.web3j.abi.datatypes.DynamicBytes(signature), 
                new Uint256(currentTimeStamp)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawGameProbe(String player, List<BigInteger> ids, List<BigInteger> amounts) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWGAMEPROBE, 
                Arrays.<Type>asList(new Address(160, player),
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(ids, Uint256.class)),
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(amounts, Uint256.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawRole(String player, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWROLE, 
                Arrays.<Type>asList(new Address(160, player),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static GamePool load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GamePool(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static GamePool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GamePool(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static GamePool load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new GamePool(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static GamePool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new GamePool(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<GamePool> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String signer_, String erc1155GameAddress_, String roleAddress_, String cyt_, String coin_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, signer_),
                new Address(160, erc1155GameAddress_),
                new Address(160, roleAddress_),
                new Address(160, cyt_),
                new Address(160, coin_)));
        return deployRemoteCall(GamePool.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<GamePool> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String signer_, String erc1155GameAddress_, String roleAddress_, String cyt_, String coin_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, signer_),
                new Address(160, erc1155GameAddress_),
                new Address(160, roleAddress_),
                new Address(160, cyt_),
                new Address(160, coin_)));
        return deployRemoteCall(GamePool.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<GamePool> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String signer_, String erc1155GameAddress_, String roleAddress_, String cyt_, String coin_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, signer_),
                new Address(160, erc1155GameAddress_),
                new Address(160, roleAddress_),
                new Address(160, cyt_),
                new Address(160, coin_)));
        return deployRemoteCall(GamePool.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<GamePool> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String signer_, String erc1155GameAddress_, String roleAddress_, String cyt_, String coin_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, signer_),
                new Address(160, erc1155GameAddress_),
                new Address(160, roleAddress_),
                new Address(160, cyt_),
                new Address(160, coin_)));
        return deployRemoteCall(GamePool.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class LoadingCoinEventEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger amount;

        public BigInteger timeStamp;
    }

    public static class LoadingCytEventEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger amount;

        public BigInteger timeStamp;
    }

    public static class LoadingGamePropeEventEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger id;

        public BigInteger amount;

        public BigInteger timeStamp;
    }

    public static class LoadingRoleEventEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger tokenId;

        public BigInteger timeStamp;
    }

    public static class WithdrawCoinEventEventResponse extends BaseEventResponse {
        public String player;

        public BigInteger amount;

        public BigInteger timeStamp;
    }

    public static class WithdrawCytEVentEventResponse extends BaseEventResponse {
        public String player;

        public BigInteger amount;

        public BigInteger timeStamp;
    }

    public static class WithdrawGameProeEventEventResponse extends BaseEventResponse {
        public String player;

        public List<BigInteger> ids;

        public List<BigInteger> amounts;

        public BigInteger timeStamp;
    }

    public static class WithdrawRoleEventEventResponse extends BaseEventResponse {
        public String player;

        public BigInteger tokenId;

        public BigInteger timeStamp;
    }
}
