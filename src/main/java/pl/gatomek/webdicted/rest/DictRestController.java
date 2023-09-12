package pl.gatomek.webdicted.rest;

import jakarta.transaction.NotSupportedException;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gatomek.webdicted.controller.DictController;
import pl.gatomek.webdicted.dto.DictQuery;
import pl.gatomek.webdicted.dto.Translation;


@RestController
@RequestMapping(
        consumes = "application/json",
        produces = "application/json",
        path = "/rest",
        method = { RequestMethod.POST}
)
public class DictRestController {
    private DictController dictController;

    public DictRestController(DictController dictController) {
        this.dictController = dictController;
    }


    @PostMapping( path = "/dict")
    @ResponseBody
    public ResponseEntity<Translation> getTranslation(@RequestBody DictQuery query) throws NotSupportedException {
        query.trim();

        CacheControl cacheControl = CacheControl.noCache().noTransform().mustRevalidate();

        Translation translation = dictController.lookup( query);
        return ResponseEntity.ok().cacheControl( cacheControl).body(translation);
    }
}
