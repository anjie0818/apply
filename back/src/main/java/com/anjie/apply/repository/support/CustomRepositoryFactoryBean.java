package com.anjie.apply.repository.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;
/**
 * 此类替代默认的RepositoryFactroyBean ,我们会获得一个CustomRepositoryFactory,然后注册自定义的Repositroy
 */
public class CustomRepositoryFactoryBean<T extends JpaRepository<S,ID>,S,ID extends Serializable> extends JpaRepositoryFactoryBean<T,S,ID>{


    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager){
        return new CustomRepositoryFactory(entityManager);
    }
    private static class CustomRepositoryFactory extends JpaRepositoryFactory{

        /**
         * Creates a new {@link JpaRepositoryFactory}.
         *
         * @param entityManager must not be {@literal null}
         */
        public CustomRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }
        @Override
        @SuppressWarnings({"unchecked"})
        protected <T,ID extends Serializable> SimpleJpaRepository<?,?> getTargetRepository(RepositoryInformation information,EntityManager entityManager){
            return new CustomerRepositoryImpl<T,ID>((Class<T>)information.getDomainType(),entityManager);

        }
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata){
            return CustomerRepositoryImpl.class;
        }
    }
}
