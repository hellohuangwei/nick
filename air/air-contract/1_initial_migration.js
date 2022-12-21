//const Migrations = artifacts.require("Migrations");

const coin = "0x6352cAb840EcC7e604199188f422680bd05Bc62A";
const airdrop = artifacts.require("airdrop"); 
module.exports = async function (deployer) { 
    // await deployer.deploy(CyberPopToken);
     //await deployer.deploy(CyberpopGame);
     await deployer.deploy(airdrop,coin);
};
