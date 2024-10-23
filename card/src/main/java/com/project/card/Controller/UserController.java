package com.project.card.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.card.Entity.*;
import com.project.card.Model.OrderRequestDTO;
import com.project.card.Repository.*;
import com.project.card.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CardService cardService;
    @Autowired
    private UserService nfcService;

    @Autowired
    private CardContactRepository cardContactRepository;

    @Autowired
    private CardSocialMediaRepository cardSocialMediaRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CardServicesRepository cardServicesRepository;

    @Autowired
    private CardProductRepository cardProductRepository;

    @GetMapping({"/digitalcards"})
    public List<DigitalCard> getdigitalcards() {
        return cardService.getdigitalcards();
    }

    @GetMapping({"/nfccards"})
    public List<NfcCard> getnfccards() {
        return cardService.getNfcCard();
    }


    @PostMapping(value = "/userNFc", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @PreAuthorize("hasRole('User')")
    public UserNfcDetails createNfcCard(
            @RequestParam(value = "nfcCardRequest", required = false) String nfcCardRequest,
            @RequestParam(value = "contact", required = false) String contact,
            @RequestParam(value = "socialMedia", required = false) String socialMedia,
            @RequestParam(value = "company", required = false) String company,
            @RequestPart(value = "cardProducts", required = false) String cardProductsJson,
            @RequestPart(value = "productImages", required = false) List<MultipartFile> productImages,
            @RequestPart(value = "cardServices", required = false) String cardServicesJson,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
            @RequestParam(value = "banner", required = false) MultipartFile banner,
            @RequestParam(value = "digitalCard", required = false) String digitalCard,
            @RequestParam(value = "nfcCard", required = false) String nfcCard

    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserNfcDetails userNfcDetails = new UserNfcDetails();
        userNfcDetails.setNfcCard(cardService.getNfcCard(Long.parseLong(nfcCard)));
        userNfcDetails.setDigitalCard(cardService.getDigitalCard(Long.parseLong(digitalCard)));


            try {
                // Convert to a Java object
                userNfcDetails = objectMapper.readValue(nfcCardRequest, UserNfcDetails.class);
                if (contact != null) {
                    userNfcDetails.setCardContact(cardContactRepository.save(objectMapper.readValue(contact, CardContact.class)));
                }
                if (socialMedia != null) {
                    userNfcDetails.setSocialMedia(cardSocialMediaRepository.save(objectMapper.readValue(socialMedia, CardSocialMedia.class)));
                }

                if (company != null) {
                    userNfcDetails.setCompany(companyRepository.save(objectMapper.readValue(company, Company.class)));
                }
                if(profilePicture!=null){
                    userNfcDetails.setProfilePicture(cardService.uploadFileToSpace(profilePicture));
                }
                if(banner!=null){
                    userNfcDetails.setBanner(cardService.uploadFileToSpace(banner));
                }

                List<CardProduct> cardProducts = objectMapper.readValue(cardProductsJson, new TypeReference<List<CardProduct>>() {
                });
                if (cardProducts.size() == productImages.size()) {
                    for (int i = 0; i < cardProducts.size(); i++) {
                        try {
                            cardProducts.get(i).setProductImage(cardService.uploadFileToSpace(productImages.get(i)));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else throw new RuntimeException("Product Images not equal to products");
                userNfcDetails.setCardProducts(cardProductRepository.saveAll(cardProducts));

                userNfcDetails.setCardServices(cardServicesRepository.saveAll(objectMapper.readValue(cardServicesJson, new TypeReference<List<CardServices>>(){})));
            } catch (Exception e) {
                e.printStackTrace();
            }


            return userService.addUserNfcDetails(userNfcDetails);

        }
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        // Delegate the business logic to the service
        Order createdOrder = orderService.createOrder(orderRequestDTO);

        // Return success message
        return ResponseEntity.ok("Order created successfully with ID: " + createdOrder.getOrderId());
    }
}


