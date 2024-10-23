package com.project.card.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_nfc_details")
public class UserNfcDetails {

    @Id
    @Column(name = "user_nfc_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String userNfcId;  // Main ID and primary key

    public String name;
    public String profilePicture;
    public String jobTitle;
    public String about;
    public String customMessage;
    public String banner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "companyId")
    public Company company;  // Foreign key relationship to the Company table

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_contact_id", referencedColumnName = "contactId")
    public CardContact cardContact;  // Foreign key relationship to the CardContact table

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_social_id", referencedColumnName = "socialId")
    public CardSocialMedia socialMedia;  // Foreign key relationship to the CardSocialMedia table

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "digital_card_id", referencedColumnName = "digitalCardId")
    public DigitalCard digitalCard;  // Foreign key relationship to the DigitalCard table

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nfc_card_id", referencedColumnName = "nfcCardId")
    public NfcCard nfcCard;  // Foreign key relationship to the NfcCard table

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nfc_id", referencedColumnName = "user_nfc_id")
    public List<CardProduct> cardProducts;  // List of card products

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nfc_id", referencedColumnName = "user_nfc_id")
    public List<CardServices> cardServices;  // List of card services

    // Constructor without getters/setters
    public UserNfcDetails(String userNsrId, String name, String profilePicture, String jobTitle,
                          String about, String customMessage, String banner, Company company,
                          CardContact cardContact, CardSocialMedia socialMedia, DigitalCard digitalCard,
                          NfcCard nfcCard, List<CardProduct> cardProducts, List<CardServices> cardServices) {
        this.userNfcId= userNsrId;
        this.name = name;
        this.profilePicture = profilePicture;
        this.jobTitle = jobTitle;
        this.about = about;
        this.customMessage = customMessage;
        this.banner = banner;
        this.company = company;
        this.cardContact = cardContact;
        this.socialMedia = socialMedia;
        this.digitalCard = digitalCard;
        this.nfcCard = nfcCard;
        this.cardProducts = cardProducts;
        this.cardServices = cardServices;
    }

    // Default constructor required by JPA
    public UserNfcDetails() {
    }

    @Override
    public String toString() {
        return "UserNfcDetails{" +
                "userNsrId='" + userNfcId + '\'' +
                ", name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", about='" + about + '\'' +
                ", customMessage='" + customMessage + '\'' +
                ", banner='" + banner + '\'' +
                ", company=" + company +
                ", cardContact=" + cardContact +
                ", socialMedia=" + socialMedia +
                ", digitalCard=" + digitalCard +
                ", nfcCard=" + nfcCard +
                ", cardProducts=" + cardProducts +
                ", cardServices=" + cardServices +
                '}';
    }
}
