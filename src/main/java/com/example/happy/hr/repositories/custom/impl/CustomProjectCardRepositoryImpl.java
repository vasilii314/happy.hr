package com.example.happy.hr.repositories.custom.impl;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.domain.entities.ProjectCard_;
import com.example.happy.hr.domain.entities.User;
import com.example.happy.hr.domain.entities.User_;
import com.example.happy.hr.domain.wrappers.ProjectCardWrapper;
import com.example.happy.hr.repositories.custom.CustomProjectCardRepository;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@AllArgsConstructor
public class CustomProjectCardRepositoryImpl implements CustomProjectCardRepository {

    private EntityManager entityManager;

    @Override
    public List<ProjectCardWrapper> getProjectCardPage(ProjectRegistryFilter filter, PageInfo pageInfo) {

        if (pageInfo == null) {
            throw new IllegalArgumentException("Page info must be specified");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProjectCardWrapper> criteriaQuery = criteriaBuilder.createQuery(ProjectCardWrapper.class);
        Root<ProjectCard> projectCardRoot = criteriaQuery.from(ProjectCard.class);
        Join<ProjectCard, User> joinUserToProjectCard = projectCardRoot.join(ProjectCard_.cardAuthor, JoinType.LEFT);

        Expression<String> fullName =
                criteriaBuilder.concat(
                        joinUserToProjectCard.get(User_.name),
                        criteriaBuilder.concat(
                                " ",
                                criteriaBuilder.concat(
                                        joinUserToProjectCard.get(User_.patronymic),
                                        criteriaBuilder.concat(
                                                " ", joinUserToProjectCard.get(User_.surname)
                                        )
                                )
                        )
                );

        criteriaQuery
                .select(
                        criteriaBuilder.construct(
                                ProjectCardWrapper.class,
                                projectCardRoot.get(ProjectCard_.id),
                                projectCardRoot.get(ProjectCard_.projectName),
                                projectCardRoot.get(ProjectCard_.projClientName),
                                fullName,
                                projectCardRoot.get(ProjectCard_.cardStatus),
                                projectCardRoot.get(ProjectCard_.functionalDirection),
                                projectCardRoot.get(ProjectCard_.subjectArea),
                                projectCardRoot.get(ProjectCard_.projectStage)
                        )
                )
                .distinct(true)
                .orderBy(
                        criteriaBuilder.asc(projectCardRoot.get(ProjectCard_.id))
                );

        if (filter != null) {
            String projectClientName = filter.getProjClientName();
            String projectCardAuthor = filter.getCardAuthor();
            String projectCardStatus = filter.getCardStatus();

            Predicate projectClientNamePredicate = projectClientName != null ?
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%" + projectClientName + "%")
                    : criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%");

            Predicate projectCardAuthorPredicate = projectCardAuthor != null ?
                    criteriaBuilder.like(fullName, "%" + projectCardAuthor + "%") :
                    criteriaBuilder.like(fullName, "%");

            Predicate projectCardStatusPredicate = projectCardStatus != null ?
                    criteriaBuilder.equal(projectCardRoot.get(ProjectCard_.cardStatus), projectCardStatus) :
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.cardStatus), "%");

            criteriaQuery.where(
                    criteriaBuilder.and(
                            projectClientNamePredicate,
                            projectCardAuthorPredicate,
                            projectCardStatusPredicate
                    )
            );
        }

        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(pageInfo.getPageSize())
                .setFirstResult((pageInfo.getPageNum() - 1) * pageInfo.getPageSize())
                .getResultList();
    }
}
