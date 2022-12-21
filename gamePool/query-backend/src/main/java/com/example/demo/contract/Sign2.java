package com.example.demo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
public class Sign2 extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506107cc806100206000396000f3fe608060405234801561001057600080fd5b506004361061005d577c01000000000000000000000000000000000000000000000000000000006000350463214c66da81146100625780635808854b146100925780636ccea652146100b3575b600080fd5b6100756100703660046104ff565b6100d6565b604051600160a060020a0390911681526020015b60405180910390f35b6100a56100a0366004610542565b6100f3565b604051908152602001610089565b6100c66100c136600461058a565b610152565b6040519015158152602001610089565b60008060006100e787878787610172565b50979650505050505050565b600061014c826040517f19457468657265756d205369676e6564204d6573736167653a0a3332000000006020820152603c8101829052600090605c01604051602081830303815290604052805190602001209050919050565b92915050565b6000610168600160a060020a038516848461025f565b90505b9392505050565b6000807f7fffffffffffffffffffffffffffffff5d576e7357a4501ddfe92f46681b20a08311156101a95750600090506003610256565b8460ff16601b141580156101c157508460ff16601c14155b156101d25750600090506004610256565b6040805160008082526020820180845289905260ff881692820192909252606081018690526080810185905260019060a0016020604051602081039080840390855afa158015610226573d6000803e3d6000fd5b5050604051601f190151915050600160a060020a03811661024f57600060019250925050610256565b9150600090505b94509492505050565b600080600061026e858561041f565b9092509050600081600481111561028757610287610663565b1480156102a5575085600160a060020a031682600160a060020a0316145b156102b55760019250505061016b565b60008087600160a060020a0316631626ba7e7c01000000000000000000000000000000000000000000000000000000000288886040516024016102f99291906106c2565b60408051601f198184030181529181526020820180517bffffffffffffffffffffffffffffffffffffffffffffffffffffffff167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1990941693909317909252905161036191906106fc565b600060405180830381855afa9150503d806000811461039c576040519150601f19603f3d011682016040523d82523d6000602084013e6103a1565b606091505b50915091508180156103b4575080516020145b8015610413575080517f1626ba7e00000000000000000000000000000000000000000000000000000000906103f29083016020908101908401610718565b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916145b98975050505050505050565b6000808251604114156104565760208301516040840151606085015160001a61044a87828585610172565b94509450505050610488565b825160401415610480576020830151604084015161047586838361048f565b935093505050610488565b506000905060025b9250929050565b6000807f7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8316816104e37f80000000000000000000000000000000000000000000000000000000000000008604601b610757565b90506104f187828885610172565b935093505050935093915050565b6000806000806080858703121561051557600080fd5b84359350602085013560ff8116811461052d57600080fd5b93969395505050506040820135916060013590565b60006020828403121561055457600080fd5b5035919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b60008060006060848603121561059f57600080fd5b8335600160a060020a03811681146105b657600080fd5b925060208401359150604084013567ffffffffffffffff808211156105da57600080fd5b818601915086601f8301126105ee57600080fd5b8135818111156106005761060061055b565b604051601f8201601f19908116603f011681019083821181831017156106285761062861055b565b8160405282815289602084870101111561064157600080fd5b8260208601602083013760006020848301015280955050505050509250925092565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b60005b838110156106ad578181015183820152602001610695565b838111156106bc576000848401525b50505050565b82815260406020820152600082518060408401526106e7816060850160208701610692565b601f01601f1916919091016060019392505050565b6000825161070e818460208701610692565b9190910192915050565b60006020828403121561072a57600080fd5b81517bffffffffffffffffffffffffffffffffffffffffffffffffffffffff198116811461016b57600080fd5b60008219821115610791577f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b50019056fea264697066735822122013a71349ab56487606d786e4a5b14d06e781b23e8a5486b62c39a90798b3589864736f6c63430008090033";

    public static final String FUNC_ISVALIDSIGNATURENOW = "isValidSignatureNow";

    public static final String FUNC_SHOW = "show";

    public static final String FUNC_VALIDSIGNATURENOW = "validSignatureNow";

    @Deprecated
    protected Sign2(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Sign2(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Sign2(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Sign2(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> isValidSignatureNow(String signer, byte[] hash, byte[] signature) {
        final Function function = new Function(FUNC_ISVALIDSIGNATURENOW, 
                Arrays.<Type>asList(new Address(160, signer),
                new Bytes32(hash),
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<byte[]> show(byte[] hash) {
        final Function function = new Function(FUNC_SHOW, 
                Arrays.<Type>asList(new Bytes32(hash)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> validSignatureNow(byte[] hash, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(FUNC_VALIDSIGNATURENOW, 
                Arrays.<Type>asList(new Bytes32(hash),
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new Bytes32(r),
                new Bytes32(s)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Sign2 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Sign2(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Sign2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Sign2(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Sign2 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Sign2(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Sign2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Sign2(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Sign2> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Sign2.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Sign2> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Sign2.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Sign2> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Sign2.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Sign2> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Sign2.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
