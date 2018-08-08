package ca.rlm.orlando.core.service.impl;

import ca.rlm.orlando.core.dao.RsvpDao;
import ca.rlm.orlando.core.entity.Rsvp;
import ca.rlm.orlando.core.service.RsvpService;

import org.springframework.stereotype.Service;


@Service
public class RsvpServiceImpl implements RsvpService {

  private final RsvpDao rsvpDao;

  public RsvpServiceImpl(final RsvpDao rsvpDao) {
    this.rsvpDao = rsvpDao;
  }

  @Override
  public Rsvp saveRsvp(Rsvp rsvp) {
    return rsvpDao.save(rsvp);
  }
}
