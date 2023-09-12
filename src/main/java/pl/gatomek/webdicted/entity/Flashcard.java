package pl.gatomek.webdicted.entity;

import jakarta.persistence.*;


@Table( name="FLASHCARD")
@Entity
public class Flashcard {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column( name="FRONT", unique = true, nullable = false)
    private String front;

    @Column( name="BACK", unique = true, nullable = false)
    private String back;

    @Column( name="ACK")
    private boolean ack;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }
}
