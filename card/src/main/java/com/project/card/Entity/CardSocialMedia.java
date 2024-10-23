package com.project.card.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "card_social_media")
public class CardSocialMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long socialId;

    public String facebook;
    public String instagram;
    public String twitter; // Now X
    public String linkedIn;
    public String snapchat;
    public String tiktok;
    public String pinterest;
    public String whatsapp;
    public String telegram;
    public String youtube;
    public String messenger;

    // Constructor without getters/setters
    public CardSocialMedia(Long socialId, String facebook, String instagram, String twitter, String linkedIn, 
                           String snapchat, String tiktok, String pinterest, String whatsapp, String telegram,
                           String youtube, String messenger) {
        this.socialId = socialId;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
        this.linkedIn = linkedIn;
        this.snapchat = snapchat;
        this.tiktok = tiktok;
        this.pinterest = pinterest;
        this.whatsapp = whatsapp;
        this.telegram = telegram;
        this.youtube = youtube;
        this.messenger = messenger;
    }

    // Default constructor required by JPA
    public CardSocialMedia() {
    }

    // Override toString method for debugging if needed
    @Override
    public String toString() {
        return "CardSocialMedia{" +
               "socialId=" + socialId +
               ", facebook='" + facebook + '\'' +
               ", instagram='" + instagram + '\'' +
               ", twitter='" + twitter + '\'' +
               ", linkedIn='" + linkedIn + '\'' +
               ", snapchat='" + snapchat + '\'' +
               ", tiktok='" + tiktok + '\'' +
               ", pinterest='" + pinterest + '\'' +
               ", whatsapp='" + whatsapp + '\'' +
               ", telegram='" + telegram + '\'' +
               ", youtube='" + youtube + '\'' +
               ", messenger='" + messenger + '\'' +
               '}';
    }
}
