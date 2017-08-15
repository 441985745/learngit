package com.opple.fa.assetcheck.model;

public enum ISDRAFT {
	//DRAFT为草稿状态，SUBMIT为申请状态
	DRAFT("0"), SUBMIT("1");
	
	private String isDraft;

	ISDRAFT(String isDraft) {
        this.isDraft = isDraft;
    }
	public String getIsDraft() {
		return isDraft;
	}
}
