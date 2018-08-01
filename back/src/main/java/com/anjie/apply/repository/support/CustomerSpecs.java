package com.anjie.apply.repository.support;
import static com.google.common.collect.Iterables.toArray;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义查询条件
 */
public class CustomerSpecs {
    //定义一个返回值为Specification的方法byAuto,传入参数为entityManage和当前的保护值为实体类的对象
    public static <T> Specification<T> byAuto(final EntityManager entityManager,final T example){
        //获得实体类对象的类的类型
        Class<T> type = (Class<T>) example.getClass();
       return new Specification<T>() {
           @Override //predicate翻译为断言
           public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
               //新建列表存放查询条件
               List<Predicate> predicates=new ArrayList<>();
                //得到实体类的entityType,可以从中得到实体类的属性
               EntityType<T> entity = entityManager.getMetamodel().entity(type);
               //对实体类的属性进行循环
               for (Attribute<T,?> attr:entity.getDeclaredAttributes())
               {
                   //获得实体类的属性值
                   Object attrValue=getValue(attr,example);
                   if (attrValue !=null){
                       //当前属性是字符串时
                       if (attr.getJavaType()==String.class){
                           //当前字符不为空的时候
                           if (!StringUtils.isEmpty(attrValue)){
                               //构造当前属性like属性值查询条件，并添加到条件列表中
                               predicates.add(criteriaBuilder.like(root.get(attribute(entity,attr.getName(),String.class)),pattern((String)attrValue)));
                           }
                       }else {
                           //其余条件构造属性和属性值equal,并添加到条件列表中
                         predicates.add(criteriaBuilder.equal(root.get(attribute(entity,attr.getName(),attrValue.getClass())),attrValue));
                       }
                   }
               }

               return predicates.isEmpty()?criteriaBuilder.conjunction():criteriaBuilder.and(toArray(predicates,Predicate.class));
           }
           /**
            * 通过实体对象属性获得属性值
            * @param attr
            * @param example
            * @param <T>
            * @return
            */
           private <T>Object getValue(Attribute<T,?>attr,T example){
               return ReflectionUtils.getField((Field) attr.getJavaMember(),example);
           }

           /**
            * 获得实体类当前属性的SingularAttribute（包含的是实体类的某个单独属性）
            * @param entity
            * @param fieldName
            * @param fieldClass
            * @param <E>
            * @param <T>
            * @return
            */

           private <E,T>SingularAttribute<T,E> attribute(EntityType<T> entity,String fieldName,Class<E> fieldClass){
               return entity.getDeclaredSingularAttribute(fieldName,fieldClass);
           }
       };
    }
    static private String pattern(String str){
        return "%"+str+"%";
    }


}
