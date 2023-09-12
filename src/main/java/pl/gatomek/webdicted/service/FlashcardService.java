package pl.gatomek.webdicted.service;

import org.springframework.stereotype.Service;
import pl.gatomek.webdicted.dto.Flashcard;

@Service
public class FlashcardService {
    public void save(Flashcard flashcardDto) {
        Flashcard flashcard = toFlashcard( flashcardDto);
    }

    private Flashcard toFlashcard(Flashcard flashcardDto) {
        Flashcard flashcard = new Flashcard();
        flashcard.setFront(flashcard.getFront());
        flashcard.setBack(flashcard.getBack());
        return flashcard;
    }
}
