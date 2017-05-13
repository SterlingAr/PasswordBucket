package com.passwordbucket.model.services.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.passwordbucket.model.services.PasswordServices;

public class PasswordServicesImpl implements PasswordServices {

	private SecureRandom random;
	private final static int MIN_LENGTH = 8;
	private final static int MAX_LENGTH = 35;
	private final static int MIN_L_CASE_COUNT = 4;
	private final static int MIN_U_CASE_COUNT = 4;
	private final static int MIN_NUM_COUNT = 8;
	private final static int MIN_SPECIAL_COUNT = 3;

	@Override
	public String generatePassword() {
		// random = new SecureRandom();
		// return new BigInteger(130, random).toString(32);
		return generateRandomString(MIN_LENGTH, MAX_LENGTH, MIN_L_CASE_COUNT, MIN_U_CASE_COUNT, MIN_NUM_COUNT,
				MIN_SPECIAL_COUNT);

	}

	private static String generateRandomString(int minLength, int maxLength, int minLCaseCount,int minUCaseCount,
			int minNumCount, int minSpecialCount) {
		char[] randomString;

		String LCaseChars = "abcdefgijkmnopqrstwxyz";
		String UCaseChars = "ABCDEFGHJKLMNPQRSTWXYZ";
		String NumericChars = "23456789";
		String SpecialChars = "*$-+?_&=!%{}/";

		Map<String, Integer> charGroupsUsed = new HashMap<String, Integer>();
		charGroupsUsed.put("lcase", minLCaseCount);
		charGroupsUsed.put("ucase", minUCaseCount);
		charGroupsUsed.put("num", minNumCount);
		charGroupsUsed.put("special", minSpecialCount);

		// Because we cannot use the default randomizer, which is based on the
		// current time (it will produce the same "random" number within a
		// second), we will use a random number generator to seed the
		// randomizer.

		// Use a 4-byte array to fill it with random bytes and convert it then
		// to an integer value.
		byte[] randomBytes = new byte[4];

		// Generate 4 random bytes.
		new Random().nextBytes(randomBytes);

		// Convert 4 bytes into a 32-bit integer value.
		int seed = (randomBytes[0] & 0x7f) << 24 | randomBytes[1] << 16 | randomBytes[2] << 8 | randomBytes[3];

		// Create a randomizer from the seed.
		Random random = new Random(seed);

		// Allocate appropriate memory for the password.
		int randomIndex = -1;
		if (minLength < maxLength) {
			randomIndex = random.nextInt((maxLength - minLength) + 1) + minLength;
			randomString = new char[randomIndex];
		} else {
			randomString = new char[minLength];
		}

		int requiredCharactersLeft = minLCaseCount + minUCaseCount + minNumCount + minSpecialCount;

		// Build the password.
		for (int i = 0; i < randomString.length; i++) {
			String selectableChars = "";

			// if we still have plenty of characters left to acheive our minimum
			// requirements.
			if (requiredCharactersLeft < randomString.length - i) {
				// choose from any group at random
				selectableChars = LCaseChars + UCaseChars + NumericChars + SpecialChars;
			} else // we are out of wiggle room, choose from a random group that
					// still needs to have a minimum required.
			{
				// choose only from a group that we need to satisfy a minimum
				// for.
				for (Map.Entry<String, Integer> charGroup : charGroupsUsed.entrySet()) {
					if ((int) charGroup.getValue() > 0) {
						if ("lcase".equals(charGroup.getKey())) {
							selectableChars += LCaseChars;
						} else if ("ucase".equals(charGroup.getKey())) {
							selectableChars += UCaseChars;
						} else if ("num".equals(charGroup.getKey())) {
							selectableChars += NumericChars;
						} else if ("special".equals(charGroup.getKey())) {
							selectableChars += SpecialChars;
						}
					}
				}
			}

			// Now that the string is built, get the next random character.
			randomIndex = random.nextInt((selectableChars.length()) - 1);
			char nextChar = selectableChars.charAt(randomIndex);

			// Tac it onto our password.
			randomString[i] = nextChar;

			// Now figure out where it came from, and decrement the appropriate
			// minimum value.
			if (LCaseChars.indexOf(nextChar) > -1) {
				charGroupsUsed.put("lcase", charGroupsUsed.get("lcase") - 1);
				if (charGroupsUsed.get("lcase") >= 0) {
					requiredCharactersLeft--;
				}
			} else if (UCaseChars.indexOf(nextChar) > -1) {
				charGroupsUsed.put("ucase", charGroupsUsed.get("ucase") - 1);
				if (charGroupsUsed.get("ucase") >= 0) {
					requiredCharactersLeft--;
				}
			} else if (NumericChars.indexOf(nextChar) > -1) {
				charGroupsUsed.put("num", charGroupsUsed.get("num") - 1);
				if (charGroupsUsed.get("num") >= 0) {
					requiredCharactersLeft--;
				}
			} else if (SpecialChars.indexOf(nextChar) > -1) {
				charGroupsUsed.put("special", charGroupsUsed.get("special") - 1);
				if (charGroupsUsed.get("special") >= 0) {
					requiredCharactersLeft--;
				}
			}
		}
		return new String(randomString);
	}

	@Override
	public String passwordStrengthMeteredResult(String password) {
		String passwordStrength = "";

		int percentageResult = passwordStrengthPercentage(password);

		if (percentageResult == 0) {
			passwordStrength = "Sin contraseña";
		}

		if (percentageResult == 25) {
			passwordStrength = "Muy débil";
		}

		if (percentageResult == 50) {
			passwordStrength = "Débil";
		}

		if (percentageResult == 75) {
			passwordStrength = "Aceptable";
		}

		if (percentageResult == 100) {
			passwordStrength = "Excelente";
		}

		return passwordStrength;

	}

	private int passwordStrengthPercentage(String password) {

		int strengthPercentage = 0;

		String[] partialRegexChecks = { ".*[a-z]+.*", // lower
				".*[A-Z]+.*", // upper
				".*[\\d]+.*", // digits
				".*[@#$%]+.*" // symbols
		};

		if (password.matches(partialRegexChecks[0])) {
			strengthPercentage += 25;
		}

		if (password.matches(partialRegexChecks[1])) {
			strengthPercentage += 25;
		}

		if (password.matches(partialRegexChecks[2])) {
			strengthPercentage += 25;
		}

		if (password.matches(partialRegexChecks[3])) {
			strengthPercentage += 25;
		}

		return strengthPercentage;

	}

}
