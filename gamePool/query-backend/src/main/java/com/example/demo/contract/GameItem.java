package com.example.demo.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please useSwagger2 the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.4.
 */
@SuppressWarnings("rawtypes")
public class GameItem extends Contract {
    public static final String BINARY = "60806040526002805460a060020a60ff0219167401000000000000000000000000000000000000000017905534801561003757600080fd5b50604051610eb5380380610eb5833981016040819052610056916100ae565b60008054600160a060020a0319908116331790915560018054600160a060020a03948516908316179055600280549290931691161790556100e1565b8051600160a060020a03811681146100a957600080fd5b919050565b600080604083850312156100c157600080fd5b6100ca83610092565b91506100d860208401610092565b90509250929050565b610dc5806100f06000396000f3fe608060405234801561001057600080fd5b50600436106100b0576000357c0100000000000000000000000000000000000000000000000000000000900480637ec6920f116100835780637ec6920f1461012d5780638da5cb5b14610140578063ac5105a014610153578063d6d6120214610166578063f382d1c61461018757600080fd5b80630a38bb9a146100b557806329550620146100e557806342966c68146101055780635bf8633a1461011a575b600080fd5b6001546100c890600160a060020a031681565b604051600160a060020a0390911681526020015b60405180910390f35b6100f86100f3366004610974565b61019a565b6040516100dc9190610a62565b610118610113366004610a7c565b6103c2565b005b6002546100c890600160a060020a031681565b61011861013b366004610a95565b61043c565b6000546100c890600160a060020a031681565b610118610161366004610ab0565b610535565b610179610174366004610ada565b610636565b6040519081526020016100dc565b610118610195366004610b3b565b610785565b600054606090600160a060020a031633146101d35760405160e560020a62461bcd0281526004016101ca90610baf565b60405180910390fd5b60025460a060020a900460ff166101ff5760405160e560020a62461bcd0281526004016101ca90610be6565b6002805460a060020a60ff0219169055845167ffffffffffffffff841614801561022a575083518551145b6102795760405160e560020a62461bcd02815260206004820152601a60248201527f4172726179206c656e6774687320646f206e6f74206d6174636800000000000060448201526064016101ca565b60005b8367ffffffffffffffff1681101561031e5786600160a060020a03167fe2542a3c139d5ec41868c9ece4b258c6e2943ec98fd8c6a47ed37fc61d191e118783815181106102cb576102cb610c1d565b60200260200101518784815181106102e5576102e5610c1d565b6020026020010151604051610304929190918252602082015260400190565b60405180910390a28061031681610c4c565b91505061027c565b506001546040517f1f7fdffa000000000000000000000000000000000000000000000000000000008152600160a060020a0390911690631f7fdffa9061036e908990899089908890600401610cdb565b600060405180830381600087803b15801561038857600080fd5b505af115801561039c573d6000803e3d6000fd5b505050508490506002805460a060020a60ff02191660a060020a17905595945050505050565b6002546040517f42966c6800000000000000000000000000000000000000000000000000000000815260048101839052600160a060020a03909116906342966c6890602401600060405180830381600087803b15801561042157600080fd5b505af1158015610435573d6000803e3d6000fd5b5050505050565b600054600160a060020a031633146104695760405160e560020a62461bcd0281526004016101ca90610baf565b60025460a060020a900460ff166104955760405160e560020a62461bcd0281526004016101ca90610be6565b6002805460a060020a60ff021981169091556040517f40d097c3000000000000000000000000000000000000000000000000000000008152600160a060020a038381166004830152909116906340d097c390602401600060405180830381600087803b15801561050457600080fd5b505af1158015610518573d6000803e3d6000fd5b50506002805460a060020a60ff02191660a060020a179055505050565b600054600160a060020a031633146105625760405160e560020a62461bcd0281526004016101ca90610baf565b60025460a060020a900460ff1661058e5760405160e560020a62461bcd0281526004016101ca90610be6565b6002805460a060020a60ff021981169091556040517fa1448194000000000000000000000000000000000000000000000000000000008152600160a060020a038481166004830152602482018490529091169063a144819490604401600060405180830381600087803b15801561060457600080fd5b505af1158015610618573d6000803e3d6000fd5b50506002805460a060020a60ff02191660a060020a17905550505050565b60008054600160a060020a031633146106645760405160e560020a62461bcd0281526004016101ca90610baf565b60025460a060020a900460ff166106905760405160e560020a62461bcd0281526004016101ca90610be6565b6002805460a060020a60ff02191690556001546040517f731133e9000000000000000000000000000000000000000000000000000000008152600160a060020a039091169063731133e9906106ef908890889088908890600401610d23565b600060405180830381600087803b15801561070957600080fd5b505af115801561071d573d6000803e3d6000fd5b50506040805187815260208101879052600160a060020a03891693507fe2542a3c139d5ec41868c9ece4b258c6e2943ec98fd8c6a47ed37fc61d191e1192500160405180910390a250826002805460a060020a60ff02191660a060020a179055949350505050565b6001546040517f6b20c454000000000000000000000000000000000000000000000000000000008152600160a060020a0390911690636b20c454906107d290869086908690600401610d5b565b600060405180830381600087803b1580156107ec57600080fd5b505af1158015610800573d6000803e3d6000fd5b50505050505050565b8035600160a060020a038116811461082057600080fd5b919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff8111828210171561087d5761087d610825565b604052919050565b600082601f83011261089657600080fd5b8135602067ffffffffffffffff8211156108b2576108b2610825565b8082026108c0828201610854565b92835284810182019282810190878511156108da57600080fd5b83870192505b848310156108f9578235825291830191908301906108e0565b979650505050505050565b600082601f83011261091557600080fd5b813567ffffffffffffffff81111561092f5761092f610825565b610942601f8201601f1916602001610854565b81815284602083860101111561095757600080fd5b816020850160208301376000918101602001919091529392505050565b600080600080600060a0868803121561098c57600080fd5b61099586610809565b9450602086013567ffffffffffffffff808211156109b257600080fd5b6109be89838a01610885565b955060408801359150808211156109d457600080fd5b6109e089838a01610885565b94506060880135915080821682146109f757600080fd5b90925060808701359080821115610a0d57600080fd5b50610a1a88828901610904565b9150509295509295909350565b600081518084526020808501945080840160005b83811015610a5757815187529582019590820190600101610a3b565b509495945050505050565b602081526000610a756020830184610a27565b9392505050565b600060208284031215610a8e57600080fd5b5035919050565b600060208284031215610aa757600080fd5b610a7582610809565b60008060408385031215610ac357600080fd5b610acc83610809565b946020939093013593505050565b60008060008060808587031215610af057600080fd5b610af985610809565b93506020850135925060408501359150606085013567ffffffffffffffff811115610b2357600080fd5b610b2f87828801610904565b91505092959194509250565b600080600060608486031215610b5057600080fd5b610b5984610809565b9250602084013567ffffffffffffffff80821115610b7657600080fd5b610b8287838801610885565b93506040860135915080821115610b9857600080fd5b50610ba586828701610885565b9150509250925092565b6020808252600c908201527f6e6f207065726d6973696f6e0000000000000000000000000000000000000000604082015260600190565b6020808252600a908201527f72652d656e746572656400000000000000000000000000000000000000000000604082015260600190565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b6000600019821415610c87577f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b5060010190565b6000815180845260005b81811015610cb457602081850181015186830182015201610c98565b81811115610cc6576000602083870101525b50601f01601f19169290920160200192915050565b600160a060020a0385168152608060208201526000610cfd6080830186610a27565b8281036040840152610d0f8186610a27565b905082810360608401526108f98185610c8e565b600160a060020a0385168152836020820152826040820152608060608201526000610d516080830184610c8e565b9695505050505050565b600160a060020a0384168152606060208201526000610d7d6060830185610a27565b8281036040840152610d518185610a2756fea26469706673582212203c0dc48a3e2ec643bc0b5568c9c30d97e4761134f3891b81e7c2a10b8d04655164736f6c63430008090033";

    public static final String FUNC_BATCHCREATEGAMEPROPE = "batchCreateGamePrope";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_CREATEGAMEPROPE = "createGamePrope";

    public static final String FUNC_createNft = "createNft";

    public static final String FUNC_ERC1155ASSETADDRESS = "erc1155AssetAddress";

    public static final String FUNC_NFTADDRESS = "nftAddress";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_UPGRADEPROPE = "upgradePrope";

    public static final Event CREATEGAMEPROPEEVENT_EVENT = new Event("createGamePropeEvent",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Uint256>() {
            }, new TypeReference<Uint256>() {
            }));
    ;

    @Deprecated
    protected GameItem(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GameItem(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected GameItem(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected GameItem(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CreateGamePropeEventEventResponse> getCreateGamePropeEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEGAMEPROPEEVENT_EVENT, transactionReceipt);
        ArrayList<CreateGamePropeEventEventResponse> responses = new ArrayList<CreateGamePropeEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateGamePropeEventEventResponse typedResponse = new CreateGamePropeEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.player = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateGamePropeEventEventResponse> createGamePropeEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CreateGamePropeEventEventResponse>() {
            @Override
            public CreateGamePropeEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CREATEGAMEPROPEEVENT_EVENT, log);
                CreateGamePropeEventEventResponse typedResponse = new CreateGamePropeEventEventResponse();
                typedResponse.log = log;
                typedResponse.player = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateGamePropeEventEventResponse> createGamePropeEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATEGAMEPROPEEVENT_EVENT));
        return createGamePropeEventEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN,
                Arrays.<Type>asList(new Uint256(tokenId)),
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

    public RemoteFunctionCall<TransactionReceipt> createNft(String player) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_createNft,
                Arrays.<Type>asList(new Address(160, player)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createNft(String player, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_createNft,
                Arrays.<Type>asList(new Address(160, player),
                        new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> erc1155AssetAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ERC1155ASSETADDRESS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> nftAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NFTADDRESS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> upgradePrope(String player, List<BigInteger> ids, List<BigInteger> amounts) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPGRADEPROPE,
                Arrays.<Type>asList(new Address(160, player),
                        new org.web3j.abi.datatypes.DynamicArray<Uint256>(
                                Uint256.class,
                                org.web3j.abi.Utils.typeMap(ids, Uint256.class)),
                        new org.web3j.abi.datatypes.DynamicArray<Uint256>(
                                Uint256.class,
                                org.web3j.abi.Utils.typeMap(amounts, Uint256.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static GameItem load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GameItem(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static GameItem load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GameItem(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static GameItem load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new GameItem(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static GameItem load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new GameItem(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<GameItem> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String erc1155AssetAddress_, String nftAddress_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, erc1155AssetAddress_),
                new Address(160, nftAddress_)));
        return deployRemoteCall(GameItem.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<GameItem> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String erc1155AssetAddress_, String nftAddress_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, erc1155AssetAddress_),
                new Address(160, nftAddress_)));
        return deployRemoteCall(GameItem.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<GameItem> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String erc1155AssetAddress_, String nftAddress_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, erc1155AssetAddress_),
                new Address(160, nftAddress_)));
        return deployRemoteCall(GameItem.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<GameItem> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String erc1155AssetAddress_, String nftAddress_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, erc1155AssetAddress_),
                new Address(160, nftAddress_)));
        return deployRemoteCall(GameItem.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class CreateGamePropeEventEventResponse extends BaseEventResponse {
        public String player;

        public BigInteger tokenId;

        public BigInteger amount;
    }
}
