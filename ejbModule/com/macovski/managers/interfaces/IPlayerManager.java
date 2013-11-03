package com.macovski.managers.interfaces;

import java.util.List;

import com.macovski.entities.Match;
import com.macovski.entities.Player;

public interface IPlayerManager extends IBaseManager<Player>{
	
	void InitializeEM();
	void InvitePlayerToMatch(Player player, Match match);
	void AcceptMatchInvite(Player player, Match match);
	void RejectMatchInvite(Player player, Match match);
	List<Player> GetAll();
	Player GetById(Long Id);
	
	/**
     * Testing Purpose Only
     */
    void AcceptMatchInviteByPlayer(Player player);
    void RejectMatchInviteByPlayer(Player player);
	
}
