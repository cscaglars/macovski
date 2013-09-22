package com.macovski.tests.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.macovski.entities.Match;
import com.macovski.entities.Player;

@Remote
public interface IBaseTestManager {

	void Insert(Object entity);
	void Delete(Object entity);
	void Update(Object entity);
	List<Object> GetAll(Object object);
	void InvitePlayerToMatch(Player player, Match match);
	void AcceptMatchInvite(Player player, Match match);
	void RejectMatchInvite(Player player, Match match);
	void AcceptMatchInviteByPlayer(Player player);
	void RejectMatchInviteByPlayer(Player player);
}
