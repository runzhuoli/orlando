package ca.rlm.orlando.springmvc.controller;

import ca.rlm.orlando.core.entity.Rsvp;
import ca.rlm.orlando.core.service.RsvpService;
import ca.rlm.orlando.springmvc.dto.rsvp.RsvpMapper;
import ca.rlm.orlando.springmvc.dto.rsvp.request.RsvpRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/rsvp")
@RestController
@Slf4j
public class RsvpController {

  private final RsvpService rsvpService;

  public RsvpController(final RsvpService rsvpService) {
    this.rsvpService = rsvpService;
  }

  /**
   * @param request
   * @param response
   * @return
   */
  @PostMapping("/")
  public Object save(@Valid @RequestBody final RsvpRequest request,
                     final HttpServletResponse response) {
    final Rsvp rsvp =
      RsvpMapper.INSTANCE.fromRsvpRequest(request);
    return rsvpService.saveRsvp(rsvp);
  }
}
