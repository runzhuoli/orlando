package ca.rlm.orlando.core.dao;

import ca.rlm.orlando.core.entity.Rsvp;

import org.springframework.data.repository.CrudRepository;


public interface RsvpDao extends CrudRepository<Rsvp, Integer> {
}
