package com.macovski.interfaces;

import javax.ejb.Remote;

import com.macovski.entities.Match;
import com.macovski.entities.Player;

@Remote
public interface IPlayerManager extends IBaseManager<Player> {
	void InitializeEM();
	void InvitePlayerToMatch(Player player, Match match);
	void AcceptMatchInvite(Player player, Match match);
	void RejectMatchInvite(Player player, Match match);
	void AcceptMatchInviteByPlayer(Player player);
	void RejectMatchInviteByPlayer(Player player);
 
}
