// Registration Validation

export const emptyFieldMessage = "* Field is Required"
export const notSelectMessage = "* Please select from list"

export const orgNameValidation = /^[A-Za-z\s]{0,30}$/
export const orgNameValidationMessage = "* Only Alphabet with Space are allowed. Max 30 Character"

export const orgEmailValidation = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/
export const orgEmailValidationMessage = "* Enter valid Email Id"

export const orgContactNoValidation = /^[0-9]{10}$/
export const orgContactNoValidationMessage = "* Enter 10 digit Number"

export const orgWebsiteUrlValidation = /(http(s)?:\/\/.)?(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/
export const orgWebsiteUrlValidationMessage = "* Enter valid Webiste url"

export const orgAddressValidation = /^[A-Za-z0-9&.,\s\\/]+$/
export const orgAddressValidationMessage = "* Only AlphaNumeric with space and special character, . / & are allowed"

export const orgFileValidationMessage = "* Only AlphaNumeric with space and special character, . / & are allowed"