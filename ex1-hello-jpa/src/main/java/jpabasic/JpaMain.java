package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 한다) - 고객의 요청마다 생성
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // CODE

            // 1. 회원 저장

            // 엔티티를 생성한 상태 (비영속)
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");

//            System.out.println("=== BEFORE ===");
            // 엔티티를 영속
//            em.persist(member);
//            System.out.println("=== AFTER ===");

            // 2. 회원 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // 3. 회원 삭제
//            em.remove(findMember);

            // 4. 회원 수정
//            findMember.setName("HelloJPA");

            // 5. JPQL
            /*
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // 1~10까지 가져옴 (페이징처리를 대신 해줌)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
             */

            // [ 영속성 컨텍스트 (영속성 관리) ]
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPAs");

//            em.persist(member);

            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("findMember.id = " + findMember1.getId());
            System.out.println("findMember.name = " + findMember1.getName());
            System.out.println("result = " + (findMember1 == findMember2));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // CLOSE
            em.close();
        }

        emf.close();
    }
}
