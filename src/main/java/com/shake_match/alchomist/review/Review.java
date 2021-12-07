package com.shake_match.alchomist.review;

import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.users.Users;
<<<<<<< HEAD
import com.shake_match.alchomist.cocktail.Cocktail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

=======
import com.shake_match.alchomist.cocktail.domain.Cocktail;
>>>>>>> c25ddf71e045f44d67a6e146dcfaa94a8a270670
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "reviews")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    int rating;

    @Column
    String description;

    @Column
    String url;

    @ManyToOne
    Users users;

    @ManyToOne
    Cocktail cocktails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Cocktail getCocktails() {
        return cocktails;
    }

    public void setCocktails(Cocktail cocktails) {
        this.cocktails = cocktails;
    }
}
