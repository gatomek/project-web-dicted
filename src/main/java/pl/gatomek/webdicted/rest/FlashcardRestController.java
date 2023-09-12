package pl.gatomek.webdicted.rest;

import org.springframework.web.bind.annotation.*;
import pl.gatomek.webdicted.dto.Flashcard;
import pl.gatomek.webdicted.service.FlashcardService;

@RestController
@RequestMapping(
        consumes = "application/json",
        produces = "application/json",
        path = "/rest/flashcard",
        method = RequestMethod.POST
)
public class FlashcardRestController {

    private final FlashcardService flashcardService;

    public FlashcardRestController( FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }
    @PostMapping( path = "/save")
    public void save( @RequestBody Flashcard flashcard) {
        flashcardService.save( flashcard);
    }
}
