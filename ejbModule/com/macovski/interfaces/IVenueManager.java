package com.macovski.interfaces;

import javax.ejb.Remote;

import com.macovski.entities.Venue;

@Remote
public interface IVenueManager extends IBaseManager<Venue> {
	void InitializeEM();
}
