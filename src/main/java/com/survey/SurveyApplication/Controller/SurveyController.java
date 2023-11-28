package com.survey.SurveyApplication.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.SurveyApplication.Model.SurveyForm;
import com.survey.SurveyApplication.Repository.SurveyRepository;





import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "http://localhost:3000")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyFormRepository;

    // GET: Retrieve all survey forms
    @GetMapping
    public List<SurveyForm> getAllSurveyForms() {
        return surveyFormRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SurveyForm> getSurveyFormById(@PathVariable int id) {
        return surveyFormRepository.findById(id)
            .map(surveyForm -> ResponseEntity.ok(surveyForm))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create a new survey form
    @PostMapping
    public ResponseEntity<SurveyForm> createSurveyForm(@RequestBody SurveyForm surveyForm) {
        SurveyForm savedSurveyForm = surveyFormRepository.save(surveyForm);
        return ResponseEntity.ok(savedSurveyForm);
    }

    // PUT: Update an existing survey form
    @PutMapping("/{id}")
    public ResponseEntity<SurveyForm> updateSurveyForm(@PathVariable int id, @RequestBody SurveyForm updatedSurveyForm) {
        return surveyFormRepository.findById(id)
            .map(surveyForm -> {
                surveyForm.setFirstName(updatedSurveyForm.getFirstName());
                surveyForm.setLastName(updatedSurveyForm.getLastName());
                surveyForm.setStreetAddress(updatedSurveyForm.getStreetAddress());
                surveyForm.setCity(updatedSurveyForm.getCity());
                surveyForm.setState(updatedSurveyForm.getState());
                surveyForm.setZip(updatedSurveyForm.getZip());
                surveyForm.setTelephone(updatedSurveyForm.getTelephone());
                surveyForm.setEmail(updatedSurveyForm.getEmail());
                surveyForm.setDateOfSurvey(updatedSurveyForm.getDateOfSurvey());
                surveyForm.setLikings(updatedSurveyForm.getLikings());
                surveyForm.setinterest(updatedSurveyForm.getinterest());
                surveyForm.setRecommendation(updatedSurveyForm.getRecommendation());
                surveyForm.setComments(updatedSurveyForm.getComments());

                // Save the updated survey form
                SurveyForm savedSurveyForm = surveyFormRepository.save(surveyForm);
                return ResponseEntity.ok(savedSurveyForm);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Delete a survey form
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSurveyForm(@PathVariable int id) {
        if (surveyFormRepository.existsById(id)) {
            surveyFormRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

