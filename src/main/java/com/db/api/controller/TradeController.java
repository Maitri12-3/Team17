package com.db.api.controller;

import com.db.api.dto.TradeDTO;
import com.db.api.model.Security;
import com.db.api.model.Trade;
import com.db.api.repository.SecurityRepository;
import com.db.api.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:8080")
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private SecurityRepository securityRepository;

//    @PostMapping
//    public ResponseEntity<String> createTrade(@RequestBody TradeDTO tradeDto) {
//        // Fetch the Security entity based on the received securityId
//        Security security = securityRepository.findById(tradeDto.getSecurityId())
//                .orElseThrow(() -> new EntityNotFoundException("Security not found"));
//
//        // Create a new Trade entity with trade details
//        Trade trade = new Trade();
//        trade.setBookId(tradeDto.getBookId());
//        trade.setCounterpartyId(tradeDto.getCounterpartyId());
//        trade.setQuantity(tradeDto.getQuantity());
//        trade.setStatus(tradeDto.getStatus());
//        trade.setPrice(tradeDto.getPrice());
//        trade.setBuySell(tradeDto.getBuySell());
//        trade.setTradeDate(tradeDto.getTradeDate());
//        trade.setSettlementDate(tradeDto.getSettlementDate());
//
//        // Associate the trade with the fetched Security entity
//        trade.setSecurity(security);
//
//        // Save the trade to the database
//        tradeRepository.save(trade);
//
//        return ResponseEntity.ok("Trade created successfully");
//    }

    @PostMapping("/securities/{securityId}/trades")
    public ResponseEntity<Trade> createTrade(@PathVariable(value = "securityId") Long securityId,
                                                 @RequestBody Trade tradeRequest) {
        Trade trade = securityRepository.findById(securityId).map(security -> {
            tradeRequest.setSecurity(security);
            return tradeRepository.save(tradeRequest);
        }).orElseThrow(() -> new RuntimeException("Not found Tutorial with id = " + securityId));

        return new ResponseEntity<>(trade, HttpStatus.CREATED);
    }

    @GetMapping("/securities/{securityId}/trades")
    public ResponseEntity<List<Trade>> getAllTradesBySecurityId(@PathVariable(value = "securityId") Long securityId) {
        if (!securityRepository.existsById(securityId)) {
            throw new RuntimeException("Not found Tutorial with id = " + securityId);
        }

        List<Trade> comments = tradeRepository.findBySecurityId(securityId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/trades/{id}")
    public ResponseEntity<Trade> getTradesByTradeId(@PathVariable(value = "id") Long id) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Comment with id = " + id));

        return new ResponseEntity<>(trade, HttpStatus.OK);
    }


//    GetMapping("/security/")
}
