/*
 * Check if use of keywords in incorrect positions is not allowed.
 * This form should fail to parse.
 */

form myForm {
    if (boolean || money || string) {
        "Question placeholder" ans: form {"Question placeholder2" ans: string}
    }
}
