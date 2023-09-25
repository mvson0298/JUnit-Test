package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;
import java.util.stream.Stream;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;
	
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}
	
	@Test
	void testRandomNumberedSquared() {
		TestDemo testDemoMock = Mockito.spy(testDemo);
		
		when(testDemoMock.getRandomInt()).thenReturn(5);
		
		int result = testDemoMock.randomNumberedSquared();
		
		verify(testDemoMock, times(1)).getRandomInt();
		
		assertEquals(25, result);
	}
	
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		
		assertThat(testDemo.addPositive(4,5)).isEqualTo(9);
		assertThat(testDemo.addPositive(40,50)).isEqualTo(90);
	}

	@ParameterizedTest
	@MethodSource("TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {

			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);

			} else {
				assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
			}
	}
	static Stream<Arguments> argumentsForAddPositive() {
		return Stream.of(
				Arguments.of(2, 3, 5, false),
				Arguments.of(5, 7, 12, false),
				Arguments.of(0, 0, 0, false),
				Arguments.of(0, 5, 0, false),
				Arguments.of(5, 0, 0, false),
				Arguments.of(-2, 3, 0, true),
				Arguments.of(5, -7, 0, true),
				Arguments.of(-5, -3, 0, true)
				);
		
	}
	
}
