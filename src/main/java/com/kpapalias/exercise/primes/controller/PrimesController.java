package com.kpapalias.exercise.primes.controller;

import com.kpapalias.exercise.primes.model.PrimesResponse;
import com.kpapalias.exercise.primes.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/primes")
public class PrimesController {

    private PrimeService primeService;

    @Autowired
    public PrimesController(final PrimeService primeService) {
        this.primeService = primeService;
    }

    @RequestMapping(value = "/{upperLimit}", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value = "primes", key = "#upperLimit")
    public PrimesResponse getPrimeNumbers(@PathVariable final int upperLimit) {
        final PrimesResponse primesResponse = new PrimesResponse(upperLimit, primeService.getPrimeNumbers(upperLimit));
        return primesResponse;
    }


}
