package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Comment;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;

public class NewCommentModel {
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment toComment(User author, Plant plant){
        Comment result = new Comment();
        result.setAuthor(author);
        result.setContent(comment);
        result.setPlant(plant);
        return result;
    }
}
