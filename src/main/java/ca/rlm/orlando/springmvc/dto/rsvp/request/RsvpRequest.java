package ca.rlm.orlando.springmvc.dto.rsvp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsvpRequest {
  private String name;
  private Integer come;
  private Integer people;
  private String message;
}
