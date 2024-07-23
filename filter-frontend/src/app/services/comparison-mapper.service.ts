import { Injectable } from '@angular/core';
import { ComparisonType } from '../enums/comparison-type.enum';
import { FieldType } from '../enums/field-type.enum';
import { comparisonTypeMap } from '../enums/comparison-type-map';

@Injectable({
    providedIn: 'root'
})
export class ComparisonMapperService {
    getComparisonTypes(field: FieldType): ComparisonType[] {
        return Object.keys(comparisonTypeMap)
            .filter(key => comparisonTypeMap[key as ComparisonType] === field)
            .map(key => key as ComparisonType);
    }

    getReadableComparisonType(type: ComparisonType): string {
        switch (type) {
            case ComparisonType.EQUAL_TO:
                return 'Equal to';
            case ComparisonType.NOT_EQUAL_TO:
                return 'Not equal to';
            case ComparisonType.GREATER_THAN:
                return 'Greater than';
            case ComparisonType.LESS_THAN:
                return 'Less than';
            case ComparisonType.GREATER_THAN_OR_EQUAL_TO:
                return 'Greater than or equal to';
            case ComparisonType.LESS_THAN_OR_EQUAL_TO:
                return 'Less than or equal to';
            case ComparisonType.STRING_EQUAL_TO:
                return 'Equal to';
            case ComparisonType.STRING_NOT_EQUAL_TO:
                return 'Not equal to';
            case ComparisonType.STRING_CONTAINS:
                return 'Contains';
            case ComparisonType.STRING_STARTS_WITH:
                return 'Starts with';
            case ComparisonType.STRING_ENDS_WITH:
                return 'Ends with';
            case ComparisonType.STRING_MATCHES:
                return 'Matches';
            case ComparisonType.DATE_EQUAL_TO:
                return 'Equal to';
            case ComparisonType.DATE_NOT_EQUAL_TO:
                return 'Not equal to';
            case ComparisonType.DATE_BEFORE:
                return 'Before';
            case ComparisonType.DATE_AFTER:
                return 'After';
            case ComparisonType.DATE_ON_OR_BEFORE:
                return 'On or before';
            case ComparisonType.DATE_ON_OR_AFTER:
                return 'On or after';
            default:
                return '';
        }
    }
}
