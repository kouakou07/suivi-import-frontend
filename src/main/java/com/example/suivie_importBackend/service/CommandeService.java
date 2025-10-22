package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Enum.StatusCommande;
import com.example.suivie_importBackend.dto.CommandeDto;
import com.example.suivie_importBackend.dto.CommandeListDto;
import com.example.suivie_importBackend.dto.LigneCommandeDto;
import com.example.suivie_importBackend.models.*;
import com.example.suivie_importBackend.repository.*;
import com.example.suivie_importBackend.vo.CommandeVO;
import com.example.suivie_importBackend.vo.LigneCommandeVO;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final FournisseurRepository fournisseurRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final FournisseurArticleRepository fournisseurArticleRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final IncotermRepository incotermRepository;

    public CommandeService(CommandeRepository commandeRepository, FournisseurRepository fournisseurRepository, UserRepository userRepository, ArticleRepository articleRepository, FournisseurArticleRepository fournisseurArticleRepository, LigneCommandeRepository ligneCommandeRepository, IncotermRepository incotermRepository) {
        this.commandeRepository = commandeRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.fournisseurArticleRepository = fournisseurArticleRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.incotermRepository = incotermRepository;
    }

    @Transactional
    public CommandeVO creerCommande(CommandeDto dto) {

        if (commandeRepository.existsByNumeroCommandeAndDeleted(dto.getNumeroCommande(), Deletion.NO)) {
            throw new IllegalArgumentException("Le numéro de commande existe déjà : " + dto.getNumeroCommande());
        }

        // 🔹 Récupérer le fournisseur
        FournisseurM fournisseur = null;
        if (dto.getFournisseurId() != null) {
            fournisseur = fournisseurRepository.findById(dto.getFournisseurId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Fournisseur introuvable avec l’ID : " + dto.getFournisseurId()));
        }

        String modeTransport = null;
        Incoterm incoterm = null;
        if (dto.getIncotermId() != null) {
            incoterm = incotermRepository.findByIdAndDeleted(dto.getIncotermId(), Deletion.NO)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Incoterm introuvable avec l’ID : " + dto.getIncotermId()));
            modeTransport = incoterm.getModeTransport().toString();
        }

        // 🔹 Création de la commande
        CommandeM commande = CommandeM.builder()
                .numeroDai(dto.getNumeroDai())
                .numeroCommande(dto.getNumeroCommande())
                .dateProforma(dto.getDateProforma())
                .incoterm(incoterm)
                .modeEnvoi(modeTransport)
                .clientDestinataire(dto.getClientDestinataire())
                .statut(StatusCommande.EN_COURS)
                .fournisseur(fournisseur)
                .lignes(new ArrayList<>())
                .build();

        CommandeM enregistrer = commandeRepository.save(commande);

        if (dto.getLignes() != null && !dto.getLignes().isEmpty()) {
            this.ajouterLignesCommande(enregistrer.getId(), dto.getLignes());
        }

        return CommandeVO.fromEntity(enregistrer);
    }

    @Transactional
    public CommandeVO ajouterLignesCommande(Long commandeId, List<LigneCommandeDto> lignes) {

        CommandeM commande = commandeRepository.findByIdAndDeleted(commandeId, Deletion.NO)
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));

        if (lignes == null || lignes.isEmpty()) {
            throw new IllegalArgumentException("Aucune ligne à ajouter");
        }

        for (LigneCommandeDto ligneDto : lignes) {
            if (ligneDto.getArticleId() == null) {
                throw new IllegalArgumentException("Article manquant pour la ligne de commande");
            }

            ArticleM article = articleRepository.findByIdAndDeleted(ligneDto.getArticleId(), Deletion.NO)
                    .orElseThrow(() -> new IllegalArgumentException("Article introuvable"));
            LigneCommande ligne = LigneCommande.builder()
                    .commande(commande)
                    .article(article)
                    .designation(ligneDto.getDesignation())
                    .uniteGestion(ligneDto.getUniteGestion())
                    .quantite(ligneDto.getQuantite())
                    .prixUnitaire(ligneDto.getPrixUnitaire())
                    .reference(ligneDto.getReference())
                    .build();

            commande.getLignes().add(ligne);
        }

        CommandeM saved = commandeRepository.save(commande);
        return CommandeVO.fromEntity(saved);
    }

    public List<LigneCommandeVO> lignesDeCommande(Long commandeId) {
        List<LigneCommande> lignes = ligneCommandeRepository.findByCommandeIdAndDeleted(commandeId, Deletion.NO);

        return lignes.stream()
                .map(LigneCommandeVO::fromEntity)
                .toList();
    }

    @Transactional
    public CommandeVO modifierCommande(Long id, CommandeDto dto) {
        CommandeM commande = commandeRepository.findByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));
        Incoterm incoterm = incotermRepository.findByIdAndDeleted(dto.getIncotermId(), Deletion.NO)
                .orElseThrow(() -> new IllegalArgumentException("Incoterm introuvable avec l’ID : " + dto.getIncotermId()));
        commande.setNumeroCommande(dto.getNumeroCommande());
        commande.setDateProforma(dto.getDateProforma());
        commande.setIncoterm(incoterm);
        commande.setModeEnvoi(incoterm.getModeTransport().toString());
        commande.setClientDestinataire(dto.getClientDestinataire());
        commande.setStatut(StatusCommande.EN_COURS);
        return CommandeVO.fromEntity(commande);
    }

    public CommandeVO DetailCommandeParId(Long id) {
        CommandeM commande = commandeRepository.findByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));
        return CommandeVO.fromEntity(commande);
    }

    public List<CommandeVO> listerCommandes() {
        return commandeRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(CommandeVO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<CommandeListDto> listerCommandesAvecPagination(Pageable pageable) {
        return commandeRepository.findAllByDeleted(Deletion.NO, pageable)
                .map(c -> CommandeListDto.builder()
                        .id(c.getId())
                        .numeroCommande(c.getNumeroCommande())
                        .numeroDai(c.getNumeroDai())
                        .clientDestinataire(c.getClientDestinataire())
                        .dateProforma(c.getDateProforma())
                        .modeEnvoi(c.getModeEnvoi())
                        .statut(c.getStatut().toString())
                        .incotermSignification(
                                c.getIncoterm() != null ? c.getIncoterm().getSignification() : null
                        )
                        .devise(c.getDevise())
                        .build()
                );
    }

    public void supprimerCommande(Long id) {
        CommandeM commande = commandeRepository.findByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));
        commande.setDeleted(Deletion.YES);
        commandeRepository.save(commande);
    }
}
