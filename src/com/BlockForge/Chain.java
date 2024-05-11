package com.BlockForge;
import java.util.ArrayList;
import java.util.Scanner;

public class Chain
{
	public static ArrayList<Blocks> blockchain = new ArrayList<Blocks>();
	public static int difficulty = 5;

	public static void main(String[] args) {
		System.out.println("Enter number of blocks to be mined in the chain?");
		Scanner scan = new Scanner(System.in);
		int blocks = scan.nextInt();
		scan.close();

		for (int i = 1; i <= blocks; i++) {
			System.out.println("Trying to Mine block " + i + "... ");
			String prev = (i == 1) ? "0" : blockchain.get(blockchain.size() - 1).hash;
			addBlock(new Blocks("Hi im here!", prev));
		}

		System.out.println("\nBlockchain is Valid: " + isChainValid());

		String blockchainJson = StringUtil.getJson(blockchain);

		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}

	public static Boolean isChainValid() {
		Blocks currentBlock;
		Blocks previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}

	public static void addBlock(Blocks newBlock)
	{
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}
