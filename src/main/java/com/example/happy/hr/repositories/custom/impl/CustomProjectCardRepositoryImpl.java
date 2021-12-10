package com.example.happy.hr.repositories.custom.impl;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.controllers.query.params.SortInfo;
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
import java.util.Map;

@AllArgsConstructor
public class CustomProjectCardRepositoryImpl implements CustomProjectCardRepository {

    private EntityManager entityManager;

    @Override
    public List<ProjectCardWrapper> getProjectCardPage(ProjectRegistryFilter filter, PageInfo pageInfo, Map<String, SortInfo> sortInfo) {

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
                );
//                .distinct(true);

        if (!sortInfo.isEmpty()) {
            if (sortInfo.containsKey("fullName"))  {
                if (sortInfo.get("fullName") == SortInfo.ASC) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(fullName));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(fullName));
                }
            }
            if (sortInfo.containsKey("id"))  {
                if (sortInfo.get("id") == SortInfo.ASC) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(projectCardRoot.get("id")));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(projectCardRoot.get("id")));
                }
            }
            if (sortInfo.containsKey("projectName"))  {
                if (sortInfo.get("projectName") == SortInfo.ASC) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(projectCardRoot.get("projectName")));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(projectCardRoot.get("projectName")));
                }
            }
            if (sortInfo.containsKey("projClientName"))  {
                if (sortInfo.get("projClientName") == SortInfo.ASC) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(projectCardRoot.get("projClientName")));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(projectCardRoot.get("projClientName")));
                }
            }
        }


        if (filter != null) {
            String projectName = filter.getProjName();
            String projectClientName = filter.getProjClientName();
            String projectCardAuthor = filter.getCardAuthor();
            String projectCardStatus = filter.getCardStatus();

            Predicate projectNamePredicate = projectName != null ?
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projectName), "%" + projectName + "%")
                    : criteriaBuilder.or(
                            criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projectName), "%"),
                            criteriaBuilder.isNull(projectCardRoot.get(ProjectCard_.projectName))
                    );

            Predicate projectClientNamePredicate = projectClientName != null ?
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%" + projectClientName + "%")
                    : criteriaBuilder.or(
                            criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%"),
                            criteriaBuilder.isNull(projectCardRoot.get(ProjectCard_.projClientName))
                    );
//                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.projClientName), "%");

            Predicate projectCardAuthorPredicate = projectCardAuthor != null ?
                    criteriaBuilder.like(fullName, "%" + projectCardAuthor + "%") :
                    criteriaBuilder.like(fullName, "%");

            Predicate projectCardStatusPredicate = projectCardStatus != null ?
                    criteriaBuilder.equal(projectCardRoot.get(ProjectCard_.cardStatus), projectCardStatus) :
                    criteriaBuilder.like(projectCardRoot.get(ProjectCard_.cardStatus), "%");

            criteriaQuery.where(
                    criteriaBuilder.and(
                            projectNamePredicate,
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

    @Override
    public ProjectCardWrapper getRegistryRecordById(Integer id) {
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
                .where(
                        criteriaBuilder.equal(projectCardRoot.get(ProjectCard_.id), id)
                );
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
