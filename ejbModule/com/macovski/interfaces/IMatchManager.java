package com.macovski.interfaces;

import javax.ejb.Remote;

import com.macovski.entities.Match;

@Remote
public interface IMatchManager extends IBaseManager<Match> {
	void InitializeEM();
}
