package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Participant;
import net.javaguides.springboot.repository.ParticipantRepository;
import net.javaguides.springboot.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    //get participants
    @GetMapping("participants")
    public List<Participant> getAllParticipant(){
        return this.participantRepository.findAll();
    }

    //get participant by id
    @GetMapping("/participants/{id}")
    public ResponseEntity<Participant> getParticipantId(@PathVariable(value = "id") Long participantId)
            throws ResourceNotFoundException {
        Participant participant= participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found for this id :: " + participantId));
                return ResponseEntity.ok().body(participant);
    }
    //save participant
    @PostMapping("participants")
    public Participant createParticipant(@RequestBody Participant participant ){
        return this.participantRepository.save(participant);
    }
    //update participant
    @PutMapping("participants/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable(value = "id") Long participantId,
            @RequestBody Participant participantDetails) throws ResourceNotFoundException{
        Participant participant= participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found for this id :: " + participantId));

        participant.setEmail_id(participantDetails.getEmail_id());
        participant.setFirst_name(participantDetails.getFirst_name());
        participant.setLast_name(participantDetails.getLast_name());

        return ResponseEntity.ok(this.participantRepository.save(participant));

    }

    //delete participant
    @DeleteMapping("participants/{id}")
    public Map<String, Boolean> deleteParticipant(@PathVariable(value = "id") Long participantId) throws ResourceNotFoundException{
        Participant participant= participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found for this id :: " + participantId));

        this.participantRepository.delete(participant);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
