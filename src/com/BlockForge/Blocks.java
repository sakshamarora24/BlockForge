package com.BlockForge;
import java.util.Date;

public class Blocks
{
	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;

	public Blocks(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
		return calculatedhash;
	}

	public void mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty);
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
