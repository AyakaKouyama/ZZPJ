package com.zzpj.controllers;

import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Opinion;
import com.zzpj.services.interfaces.OpinionService;
import com.zzpj.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/opinions")
public class OpinionController extends BaseController<Opinion, OpinionDto> {

    private OpinionService service;

    public OpinionController(OpinionService baseService) {
        super(baseService);
        this.service = baseService;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<OpinionDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody OpinionDto dto) {
        OpinionDto created = service.add(dto);
        return new ResponseEntity<OpinionDto>(created, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<OpinionDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody OpinionDto dto) {
        return super.update(id, dto);
    }


    @Override
    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{userId}/user", method = RequestMethod.GET)
    ResponseEntity<List<OpinionDto>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{bookId}/book", method = RequestMethod.GET)
    ResponseEntity<List<OpinionDto>> findByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(service.findByBookId(bookId));
    }

    @PreAuthorize("hasAnyAuthority('" + Constants.ADMINISTRATOR + ", " + Constants.CLIENT + "')")
    @RequestMapping(value = "/{bookId}/book/avg", method = RequestMethod.GET)
    ResponseEntity<Integer> getAverageRateForBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(service.getAverageRateForBook(bookId));
    }
}
