package ca.rlm.orlando.springmvc.dto.rsvp;

import ca.rlm.orlando.core.entity.Rsvp;
import ca.rlm.orlando.springmvc.dto.rsvp.request.RsvpRequest;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RsvpMapper {
  RsvpMapper INSTANCE = Mappers.getMapper(RsvpMapper.class);

  Rsvp fromRsvpRequest(RsvpRequest request);
}
