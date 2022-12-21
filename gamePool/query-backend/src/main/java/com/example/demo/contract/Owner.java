package com.example.demo.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
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
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.4.
 */
@SuppressWarnings("rawtypes")
public class Owner extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a0319163390811782556040519091907f342827c97908e5e2f71151c08502a66d44b6f758e3ac2f1de95f02eb95f0a735908290a361022d8061005f6000396000f3fe608060405234801561001057600080fd5b506004361061005d577c010000000000000000000000000000000000000000000000000000000060003504635d1ca6318114610062578063893d20e814610076578063a6f9dae11461009e575b600080fd5b604051600181526020015b60405180910390f35b60005460405173ffffffffffffffffffffffffffffffffffffffff909116815260200161006d565b6100b16100ac3660046101ba565b6100b3565b005b60005473ffffffffffffffffffffffffffffffffffffffff163314610138576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601360248201527f43616c6c6572206973206e6f74206f776e657200000000000000000000000000604482015260640160405180910390fd5b6000805460405173ffffffffffffffffffffffffffffffffffffffff808516939216917f342827c97908e5e2f71151c08502a66d44b6f758e3ac2f1de95f02eb95f0a73591a36000805473ffffffffffffffffffffffffffffffffffffffff191673ffffffffffffffffffffffffffffffffffffffff92909216919091179055565b6000602082840312156101cc57600080fd5b813573ffffffffffffffffffffffffffffffffffffffff811681146101f057600080fd5b939250505056fea2646970667358221220e68006be6c93af6989df2d2ca2080b65c34d5ef86622473a11f19bf42cdfb0ec64736f6c63430008090033";

    public static final String FUNC_CHANGEOWNER = "changeOwner";

    public static final String FUNC_GETID = "getId";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final Event OWNERSET_EVENT = new Event("OwnerSet",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
            }, new TypeReference<Address>(true) {
            }));
    ;

    @Deprecated
    protected Owner(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Owner(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Owner(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Owner(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<OwnerSetEventResponse> getOwnerSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSET_EVENT, transactionReceipt);
        ArrayList<OwnerSetEventResponse> responses = new ArrayList<OwnerSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnerSetEventResponse typedResponse = new OwnerSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnerSetEventResponse> ownerSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnerSetEventResponse>() {
            @Override
            public OwnerSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSET_EVENT, log);
                OwnerSetEventResponse typedResponse = new OwnerSetEventResponse();
                typedResponse.log = log;
                typedResponse.oldOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnerSetEventResponse> ownerSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSET_EVENT));
        return ownerSetEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> changeOwner(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CHANGEOWNER,
                Arrays.<Type>asList(new Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getId() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETID,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Owner load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Owner(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Owner load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Owner(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Owner load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Owner(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Owner load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Owner(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Owner> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Owner.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Owner> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Owner.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Owner> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Owner.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Owner> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Owner.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OwnerSetEventResponse extends BaseEventResponse {
        public String oldOwner;

        public String newOwner;
    }
}
