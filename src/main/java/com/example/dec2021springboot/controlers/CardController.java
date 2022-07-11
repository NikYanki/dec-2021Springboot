package com.example.dec2021springboot.controlers;

import com.example.dec2021springboot.dao.CardDAO;
import com.example.dec2021springboot.models.Card;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    private CardDAO cardDAO;

    public CardController(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

//    @GetMapping("")
//    public ResponseEntity<List<Card>> getCardList() {
//        List<Card> cardList = cardDAO.findAll();
//        return new ResponseEntity<>(cardList, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Card> getCardById(@PathVariable int id) {
//        return new ResponseEntity<>(cardDAO.findById(id).get(), HttpStatus.OK);
//    }
//
//    @PostMapping("")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void saveCard(@RequestBody Card passport) {
//        cardDAO.save(passport);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void deleteById(@PathVariable int id) {
//        cardDAO.deleteById(id);
//    }
//
//    @PatchMapping("")
//    public ResponseEntity<Card> patchCard(@RequestBody Card card) {
//        Card newCard = cardDAO.save(card);
//        return new ResponseEntity<>(newCard, HttpStatus.ACCEPTED);
//    }
}

