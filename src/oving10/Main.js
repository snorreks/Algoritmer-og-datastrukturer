/*
^ : start of string/line
A-Za-z : match characters "A' through "Z" and "a" through "z"
+ : match 1 or more times
$ : match end of string/line
now to put it together would be
/[A-Za-z]+$/
or
/A-Z]+$/i
*/

const regex1 = RegExp(/\d/);
const regex2 = RegExp(/^\d{2}[./-]\d{2}[./-]\d{4}$/);
const regex3 = RegExp(/^\S{10,}$/);
const regex4 = RegExp(/[^A-Za-zÆØÅæøå]+\S*$/);

const str1 = 'her er et tall 4 lol';
const str2 = '14/05/2007';
const str3 = 'asdfg`^asdatr45';
const str4 = 'asdfgt*rÆdsdsd';

console.log('Opgpave 1: ' + regex1.test(str1));
console.log('Opgpave 2: ' + regex2.test(str2));
console.log('Opgpave 3: ' + regex3.test(str3));
console.log('Opgpave 4: ' + regex4.test(str4));