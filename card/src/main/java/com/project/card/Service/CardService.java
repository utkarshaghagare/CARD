package com.project.card.Service;

import com.project.card.Entity.*;
import com.project.card.Repository.*;
import com.project.card.Security.JwtHelper;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class CardService {
    private final S3Client s3Client;
    private final String bucketName = "studycycle";
    private final String spaceUrl = "https://studycycle.blr1.digitaloceanspaces.com/";
    @Autowired
    private JwtHelper jwtHelper;

    public CardService(S3Client s3Client){this.s3Client=s3Client;}

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DigitalCardRepository digitalCardRepository;

    @Autowired
    private NfcCardRepository nfcCardRepository;

    public String uploadFileToSpace(MultipartFile file) throws IOException, java.io.IOException {
        String fileName = UUID.randomUUID().toString() + "-" + StringUtils.cleanPath(file.getOriginalFilename());

        // Validate file
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        Path tempFile = Files.createTempFile("upload-", fileName);

        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        // Upload to DigitalOcean Spaces
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .acl("public-read")
                .build();

        s3Client.putObject(putObjectRequest, tempFile);

        // Clean up temporary file
        Files.deleteIfExists(tempFile);

        // Return the file URL
        return spaceUrl + fileName;
    }
    public List<DigitalCard> getdigitalcards() {
        return digitalCardRepository.findAll();
    }

    public DigitalCard adddigitalcards(DigitalCard digitalCard) {
        return digitalCardRepository.save(digitalCard);
    }

    public NfcCard addNfcCard(NfcCard nfcCard) {
        return nfcCardRepository.save(nfcCard);
    }

    public List<NfcCard> getNfcCard() {
        return nfcCardRepository.findAll();
    }

    public NfcCard getNfcCard(long nfcCardId) {
        NfcCard nfcCard= nfcCardRepository.findById(nfcCardId).orElse(null);
        if (nfcCard!= null){
            return nfcCard;
        }
        throw new RuntimeException("Select Valid NFC CARD DESIGN");

    }
    public DigitalCard getDigitalCard(long digitalCardId) {
        DigitalCard digitalCard= digitalCardRepository.findById( digitalCardId).orElse(null);

        if (digitalCard!= null){
            return digitalCard;
        }
        throw new RuntimeException("Select Valid Digital CARD DESIGN");
    }
}
