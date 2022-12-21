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
public class GameLogic extends Contract {
    public static final String BINARY = "60806040526004805460a060020a60ff021916740100000000000000000000000000000000000000001790553480156200003857600080fd5b506040516200197e3803806200197e8339810160408190526200005b91620000d8565b6000805433600160a060020a031991821617909155600180548216600160a060020a039586161790556002805482169385169390931790925560038054831694841694909417909355600480549091169290911691909117905562000135565b8051600160a060020a0381168114620000d357600080fd5b919050565b60008060008060808587031215620000ef57600080fd5b620000fa85620000bb565b93506200010a60208601620000bb565b92506200011a60408601620000bb565b91506200012a60608601620000bb565b905092959194509250565b61183980620001456000396000f3fe608060405234801561001057600080fd5b506004361061009a576000357c01000000000000000000000000000000000000000000000000000000009004806373704f441161007857806373704f44146101045780638da5cb5b14610117578063b21e5b9f1461012a578063d6d612021461013d57600080fd5b806325fb55291461009f57806326673073146100b457806329550620146100e4575b600080fd5b6100b26100ad3660046112b3565b61015e565b005b6001546100c790600160a060020a031681565b604051600160a060020a0390911681526020015b60405180910390f35b6100f76100f2366004611416565b61022c565b6040516100db9190611504565b6002546100c790600160a060020a031681565b6000546100c790600160a060020a031681565b6100b2610138366004611517565b610410565b61015061014b366004611567565b6106cb565b6040519081526020016100db565b600054600160a060020a031633146101a9576040517f8e4a23d60000000000000000000000000000000000000000000000000000000081523360048201526024015b60405180910390fd5b6002546040517fa1448194000000000000000000000000000000000000000000000000000000008152600160a060020a038481166004830152602482018490529091169063a144819490604401600060405180830381600087803b15801561021057600080fd5b505af1158015610224573d6000803e3d6000fd5b505050505050565b600054606090600160a060020a03163314610275576040517f8e4a23d60000000000000000000000000000000000000000000000000000000081523360048201526024016101a0565b84518367ffffffffffffffff16148015610290575083518551145b6102df5760405160e560020a62461bcd02815260206004820152601a60248201527f4172726179206c656e6774687320646f206e6f74206d6174636800000000000060448201526064016101a0565b60005b8367ffffffffffffffff168110156103845786600160a060020a03167fbcf0904f7a984efd7cb9c250a5ea2fd219050ebe037783317053e86ae78bda59878381518110610331576103316115c8565b602002602001015187848151811061034b5761034b6115c8565b602002602001015160405161036a929190918252602082015260400190565b60405180910390a28061037c816115fa565b9150506102e2565b506001546040517f1f7fdffa000000000000000000000000000000000000000000000000000000008152600160a060020a0390911690631f7fdffa906103d4908990899089908890600401611671565b600060405180830381600087803b1580156103ee57600080fd5b505af1158015610402573d6000803e3d6000fd5b509698975050505050505050565b60045474010000000000000000000000000000000000000000900460ff1661047d5760405160e560020a62461bcd02815260206004820152600a60248201527f72652d656e74657265640000000000000000000000000000000000000000000060448201526064016101a0565b6004805474ff0000000000000000000000000000000000000000191690556040516005906104ac9084906116b9565b90815260200160405180910390205460001461050d5760405160e560020a62461bcd02815260206004820152601160248201527f50726f6f6620686173206578706972656400000000000000000000000000000060448201526064016101a0565b60035460408051808201909152600981527f4552433732315f62640000000000000000000000000000000000000000000000602082015261055d91600160a060020a0316908590859085906107e0565b15156001146105b15760405160e560020a62461bcd02815260206004820152601d60248201527f596f7520646f6e277420676574207468652070726f6f6620726967687400000060448201526064016101a0565b600254600480546040517fa1448194000000000000000000000000000000000000000000000000000000008152600160a060020a0391821692810192909252602482018690529091169063a144819490604401600060405180830381600087803b15801561061e57600080fd5b505af1158015610632573d6000803e3d6000fd5b50505050600160058360405161064891906116b9565b9081526040805160209281900383018120939093553383529082018590527f04f6061b1d222031de9ca9df0bc8c1539107aa90c8855894710aef65884d1d03910160405180910390a150506004805474ff000000000000000000000000000000000000000019167401000000000000000000000000000000000000000017905550565b60008054600160a060020a03163314610712576040517f8e4a23d60000000000000000000000000000000000000000000000000000000081523360048201526024016101a0565b6001546040517f731133e9000000000000000000000000000000000000000000000000000000008152600160a060020a039091169063731133e9906107619088908890889088906004016116d5565b600060405180830381600087803b15801561077b57600080fd5b505af115801561078f573d6000803e3d6000fd5b50506040805187815260208101879052600160a060020a03891693507fbcf0904f7a984efd7cb9c250a5ea2fd219050ebe037783317053e86ae78bda5992500160405180910390a250919392505050565b600061080886610802336107f389610812565b6107fc88610812565b87610937565b866109c9565b9695505050505050565b60608161085257505060408051808201909152600181527f3000000000000000000000000000000000000000000000000000000000000000602082015290565b8160005b811561087c5780610866816115fa565b91506108759050600a8361171c565b9150610856565b60008167ffffffffffffffff811115610897576108976112dd565b6040519080825280601f01601f1916602001820160405280156108c1576020820181803683370190505b5090505b841561092f576108d6600183611730565b91506108e3600a86611747565b6108ee90603061175b565b60f860020a02818381518110610906576109066115c8565b6020010190600160f860020a031916908160001a905350610928600a8661171c565b94506108c5565b949350505050565b60006109c061095061094887610b8b565b868686610bd0565b60405160200161096091906116b9565b60408051601f1981840301815282825280516020918201207f19457468657265756d205369676e6564204d6573736167653a0a33320000000084830152603c8085019190915282518085039091018152605c909301909152815191012090565b95945050505050565b60008060006109d88585610e3f565b909250905060008160048111156109f1576109f1611773565b148015610a0f575085600160a060020a031682600160a060020a0316145b15610a1f57600192505050610b84565b60008087600160a060020a0316631626ba7e7c0100000000000000000000000000000000000000000000000000000000028888604051602401610a6392919061178c565b60408051601f198184030181529181526020820180517bffffffffffffffffffffffffffffffffffffffffffffffffffffffff167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19909416939093179092529051610acb91906116b9565b600060405180830381855afa9150503d8060008114610b06576040519150601f19603f3d011682016040523d82523d6000602084013e610b0b565b606091505b5091509150818015610b1e575080516020145b8015610b7d575080517f1626ba7e0000000000000000000000000000000000000000000000000000000090610b5c90830160209081019084016117a5565b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916145b9450505050505b9392505050565b6040516c01000000000000000000000000600160a060020a038316026020820152606090610bca90603401604051602081830303815290604052610eaf565b92915050565b8051825184518651606093889388938893889360009390929091610bf4919061175b565b610bfe919061175b565b610c08919061175b565b67ffffffffffffffff811115610c2057610c206112dd565b6040519080825280601f01601f191660200182016040528015610c4a576020820181803683370190505b509050806000805b8751811015610cc657878181518110610c6d57610c6d6115c8565b602001015160f860020a900460f860020a02838380610c8b906115fa565b945081518110610c9d57610c9d6115c8565b6020010190600160f860020a031916908160001a90535080610cbe816115fa565b915050610c52565b5060005b8651811015610d3e57868181518110610ce557610ce56115c8565b602001015160f860020a900460f860020a02838380610d03906115fa565b945081518110610d1557610d156115c8565b6020010190600160f860020a031916908160001a90535080610d36816115fa565b915050610cca565b5060005b8551811015610db657858181518110610d5d57610d5d6115c8565b602001015160f860020a900460f860020a02838380610d7b906115fa565b945081518110610d8d57610d8d6115c8565b6020010190600160f860020a031916908160001a90535080610dae816115fa565b915050610d42565b5060005b8451811015610e2e57848181518110610dd557610dd56115c8565b602001015160f860020a900460f860020a02838380610df3906115fa565b945081518110610e0557610e056115c8565b6020010190600160f860020a031916908160001a90535080610e26816115fa565b915050610dba565b50919b9a5050505050505050505050565b600080825160411415610e765760208301516040840151606085015160001a610e6a8782858561113a565b94509450505050610ea8565b825160401415610ea05760208301516040840151610e95868383611227565b935093505050610ea8565b506000905060025b9250929050565b60408051808201909152601081527f30313233343536373839616263646566000000000000000000000000000000006020820152815160609190600090610ef79060026117e4565b610f0290600261175b565b67ffffffffffffffff811115610f1a57610f1a6112dd565b6040519080825280601f01601f191660200182016040528015610f44576020820181803683370190505b5090507f300000000000000000000000000000000000000000000000000000000000000081600081518110610f7b57610f7b6115c8565b6020010190600160f860020a031916908160001a9053507f780000000000000000000000000000000000000000000000000000000000000081600181518110610fc657610fc66115c8565b6020010190600160f860020a031916908160001a90535060005b845181101561113257826004868381518110610ffe57610ffe6115c8565b602001015160f860020a900460f860020a02600160f860020a031916908060020a820491505060f860020a900460ff168151811061103e5761103e6115c8565b016020015160f860020a9081900402826110598360026117e4565b61106490600261175b565b81518110611074576110746115c8565b6020010190600160f860020a031916908160001a9053508285828151811061109e5761109e6115c8565b602001015160f860020a900460f860020a02600f60f860020a021660f860020a900460ff16815181106110d3576110d36115c8565b016020015160f860020a9081900402826110ee8360026117e4565b6110f990600361175b565b81518110611109576111096115c8565b6020010190600160f860020a031916908160001a9053508061112a816115fa565b915050610fe0565b509392505050565b6000807f7fffffffffffffffffffffffffffffff5d576e7357a4501ddfe92f46681b20a0831115611171575060009050600361121e565b8460ff16601b1415801561118957508460ff16601c14155b1561119a575060009050600461121e565b6040805160008082526020820180845289905260ff881692820192909252606081018690526080810185905260019060a0016020604051602081039080840390855afa1580156111ee573d6000803e3d6000fd5b5050604051601f190151915050600160a060020a0381166112175760006001925092505061121e565b9150600090505b94509492505050565b6000807f7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff83168161127b7f80000000000000000000000000000000000000000000000000000000000000008604601b61175b565b90506112898782888561113a565b935093505050935093915050565b8035600160a060020a03811681146112ae57600080fd5b919050565b600080604083850312156112c657600080fd5b6112cf83611297565b946020939093013593505050565b60e060020a634e487b7102600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff8111828210171561131f5761131f6112dd565b604052919050565b600082601f83011261133857600080fd5b8135602067ffffffffffffffff821115611354576113546112dd565b8082026113628282016112f6565b928352848101820192828101908785111561137c57600080fd5b83870192505b8483101561139b57823582529183019190830190611382565b979650505050505050565b600082601f8301126113b757600080fd5b813567ffffffffffffffff8111156113d1576113d16112dd565b6113e4601f8201601f19166020016112f6565b8181528460208386010111156113f957600080fd5b816020850160208301376000918101602001919091529392505050565b600080600080600060a0868803121561142e57600080fd5b61143786611297565b9450602086013567ffffffffffffffff8082111561145457600080fd5b61146089838a01611327565b9550604088013591508082111561147657600080fd5b61148289838a01611327565b945060608801359150808216821461149957600080fd5b909250608087013590808211156114af57600080fd5b506114bc888289016113a6565b9150509295509295909350565b600081518084526020808501945080840160005b838110156114f9578151875295820195908201906001016114dd565b509495945050505050565b602081526000610b8460208301846114c9565b60008060006060848603121561152c57600080fd5b83359250602084013567ffffffffffffffff81111561154a57600080fd5b611556868287016113a6565b925050604084013590509250925092565b6000806000806080858703121561157d57600080fd5b61158685611297565b93506020850135925060408501359150606085013567ffffffffffffffff8111156115b057600080fd5b6115bc878288016113a6565b91505092959194509250565b60e060020a634e487b7102600052603260045260246000fd5b60e060020a634e487b7102600052601160045260246000fd5b600060001982141561160e5761160e6115e1565b5060010190565b60005b83811015611630578181015183820152602001611618565b8381111561163f576000848401525b50505050565b6000815180845261165d816020860160208601611615565b601f01601f19169290920160200192915050565b600160a060020a038516815260806020820152600061169360808301866114c9565b82810360408401526116a581866114c9565b9050828103606084015261139b8185611645565b600082516116cb818460208701611615565b9190910192915050565b600160a060020a03851681528360208201528260408201526080606082015260006108086080830184611645565b60e060020a634e487b7102600052601260045260246000fd5b60008261172b5761172b611703565b500490565b600082821015611742576117426115e1565b500390565b60008261175657611756611703565b500690565b6000821982111561176e5761176e6115e1565b500190565b60e060020a634e487b7102600052602160045260246000fd5b82815260406020820152600061092f6040830184611645565b6000602082840312156117b757600080fd5b81517bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1981168114610b8457600080fd5b60008160001904831182151516156117fe576117fe6115e1565b50029056fea26469706673582212201ac2cd2d7518633bd6563241d89e4067937d43c12cfc7e1975df74b5c620a2cd64736f6c63430008090033";

    public static final String FUNC_BATCHCREATEGAMEPROPE = "batchCreateGamePrope";

    public static final String FUNC_CREATEGAMEPROPE = "createGamePrope";

    public static final String FUNC_CREATEROLE = "createRole";

    public static final String FUNC_ERC1155GAMEADDRESS = "erc1155GameAddress";

    public static final String FUNC_MERGETOBD = "mergeToBd";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ROLEADDRESS = "roleAddress";

    public static final Event CREATEGAMEEVENT_EVENT = new Event("createGameEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event MERGETOBDEVENT_EVENT = new Event("mergeToBdEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected GameLogic(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GameLogic(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected GameLogic(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected GameLogic(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CreateGameEventEventResponse> getCreateGameEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEGAMEEVENT_EVENT, transactionReceipt);
        ArrayList<CreateGameEventEventResponse> responses = new ArrayList<CreateGameEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateGameEventEventResponse typedResponse = new CreateGameEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.player = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateGameEventEventResponse> createGameEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CreateGameEventEventResponse>() {
            @Override
            public CreateGameEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CREATEGAMEEVENT_EVENT, log);
                CreateGameEventEventResponse typedResponse = new CreateGameEventEventResponse();
                typedResponse.log = log;
                typedResponse.player = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateGameEventEventResponse> createGameEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATEGAMEEVENT_EVENT));
        return createGameEventEventFlowable(filter);
    }

    public List<MergeToBdEventEventResponse> getMergeToBdEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MERGETOBDEVENT_EVENT, transactionReceipt);
        ArrayList<MergeToBdEventEventResponse> responses = new ArrayList<MergeToBdEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MergeToBdEventEventResponse typedResponse = new MergeToBdEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MergeToBdEventEventResponse> mergeToBdEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MergeToBdEventEventResponse>() {
            @Override
            public MergeToBdEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MERGETOBDEVENT_EVENT, log);
                MergeToBdEventEventResponse typedResponse = new MergeToBdEventEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.newId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MergeToBdEventEventResponse> mergeToBdEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MERGETOBDEVENT_EVENT));
        return mergeToBdEventEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> batchCreateGamePrope(String player, List<BigInteger> tokenIds, List<BigInteger> amounts, BigInteger length, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BATCHCREATEGAMEPROPE, 
                Arrays.<Type>asList(new Address(160, player),
                new org.web3j.abi.datatypes.DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(tokenIds, Uint256.class)),
                new org.web3j.abi.datatypes.DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(amounts, Uint256.class)),
                new org.web3j.abi.datatypes.generated.Uint64(length), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createGamePrope(String player, BigInteger tokenId, BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEGAMEPROPE, 
                Arrays.<Type>asList(new Address(160, player),
                new Uint256(tokenId),
                new Uint256(amount),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createRole(String player, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEROLE, 
                Arrays.<Type>asList(new Address(160, player),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> erc1155GameAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ERC1155GAMEADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mergeToBd(BigInteger newId, byte[] signature, BigInteger currentTimeStamp) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MERGETOBD, 
                Arrays.<Type>asList(new Uint256(newId),
                new org.web3j.abi.datatypes.DynamicBytes(signature), 
                new Uint256(currentTimeStamp)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> roleAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ROLEADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static GameLogic load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GameLogic(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static GameLogic load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GameLogic(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static GameLogic load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new GameLogic(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static GameLogic load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new GameLogic(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<GameLogic> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String signer_, String erc1155GameAddress_, String roleAddress_, String gamePoolAddress_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, signer_),
                new Address(160, erc1155GameAddress_),
                new Address(160, roleAddress_),
                new Address(160, gamePoolAddress_)));
        return deployRemoteCall(GameLogic.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<GameLogic> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String signer_, String erc1155GameAddress_, String roleAddress_, String gamePoolAddress_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, signer_),
                new Address(160, erc1155GameAddress_),
                new Address(160, roleAddress_),
                new Address(160, gamePoolAddress_)));
        return deployRemoteCall(GameLogic.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<GameLogic> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String signer_, String erc1155GameAddress_, String roleAddress_, String gamePoolAddress_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, signer_),
                new Address(160, erc1155GameAddress_),
                new Address(160, roleAddress_),
                new Address(160, gamePoolAddress_)));
        return deployRemoteCall(GameLogic.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<GameLogic> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String signer_, String erc1155GameAddress_, String roleAddress_, String gamePoolAddress_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, signer_),
                new Address(160, erc1155GameAddress_),
                new Address(160, roleAddress_),
                new Address(160, gamePoolAddress_)));
        return deployRemoteCall(GameLogic.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class CreateGameEventEventResponse extends BaseEventResponse {
        public String player;

        public BigInteger tokenId;

        public BigInteger amount;
    }

    public static class MergeToBdEventEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger newId;
    }
}
