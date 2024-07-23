import { ComparisonType } from './comparison-type.enum';
import { FieldType } from './field-type.enum';

export const comparisonTypeMap: { [key in ComparisonType]: FieldType } = {
    [ComparisonType.EQUAL_TO]: FieldType.AMOUNT,
    [ComparisonType.NOT_EQUAL_TO]: FieldType.AMOUNT,
    [ComparisonType.GREATER_THAN]: FieldType.AMOUNT,
    [ComparisonType.LESS_THAN]: FieldType.AMOUNT,
    [ComparisonType.GREATER_THAN_OR_EQUAL_TO]: FieldType.AMOUNT,
    [ComparisonType.LESS_THAN_OR_EQUAL_TO]: FieldType.AMOUNT,
    [ComparisonType.STRING_EQUAL_TO]: FieldType.TITLE,
    [ComparisonType.STRING_NOT_EQUAL_TO]: FieldType.TITLE,
    [ComparisonType.STRING_CONTAINS]: FieldType.TITLE,
    [ComparisonType.STRING_STARTS_WITH]: FieldType.TITLE,
    [ComparisonType.STRING_ENDS_WITH]: FieldType.TITLE,
    [ComparisonType.STRING_MATCHES]: FieldType.TITLE,
    [ComparisonType.DATE_EQUAL_TO]: FieldType.DATE,
    [ComparisonType.DATE_NOT_EQUAL_TO]: FieldType.DATE,
    [ComparisonType.DATE_BEFORE]: FieldType.DATE,
    [ComparisonType.DATE_AFTER]: FieldType.DATE,
    [ComparisonType.DATE_ON_OR_BEFORE]: FieldType.DATE,
    [ComparisonType.DATE_ON_OR_AFTER]: FieldType.DATE
};
