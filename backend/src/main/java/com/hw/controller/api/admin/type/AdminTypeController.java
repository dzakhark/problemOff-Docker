package com.hw.controller.api.admin.type;

import com.hw.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("types")
public class AdminTypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/{id}/category/{categoryId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity setCategory(@PathVariable Long id,
                                  @PathVariable Long categoryId,
                                  PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(
                assembler.toFullResource(typeService.setCategory(id, categoryId))
        );
    }

}
