package lectureBasic.core.beanfind

import MemberServiceImpl
import lectureBasic.core.AppConfig
import lectureBasic.core.member.MemberService
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextBasicFindTest {

    val ac: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("빈 이름으로 조회")
    fun findBeanByName() {
        val memberService = ac.getBean("memberService", MemberService::class.java)
        println("memberService = $memberService")
        println("memberService.getClass() = ${memberService.javaClass}")

        assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    fun findBeanByType() {
        val memberService = ac.getBean(MemberService::class.java)
        println("memberService = $memberService")
        println("memberService.getClass() = ${memberService.javaClass}")

        assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @Test
    @DisplayName("구체 타입으로 조회") // 단, 유연성이 떨어진다.
    fun findBeanByName2() {
        val memberService = ac.getBean("memberService", MemberServiceImpl::class.java)
        println("memberService = $memberService")
        println("memberService.getClass() = ${memberService.javaClass}")

        assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패")
    fun FailFindBeanByName() {
        assertThrows(NoSuchBeanDefinitionException::class.java) { ac.getBean("xxx", MemberService::class.java) }
    }
}
