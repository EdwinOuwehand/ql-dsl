form QonditionQuestions {
	"What is your first name?" firstName: string
	if (true) {
		"What is your last name?" lastName: string
	} else {
		"What is your middle name?" middleName: string
        if (false) {
            "What is your last name?" lastName: string
        } else {
            "What is your middle name?" middleName: string
        }
	}
}