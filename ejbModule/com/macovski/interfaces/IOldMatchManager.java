package com.macovski.interfaces;

import javax.ejb.Remote;

import com.macovski.entities.OldMatch;

@Remote
public interface IOldMatchManager extends IBaseManager<OldMatch> {
	void InitializeEM();
}
