import { ComparisonType } from "../enums/comparison-type.enum";
import { FieldType } from "../enums/field-type.enum";

export interface Criteria {
  field: FieldType;
  comparison: ComparisonType;
  value: any;
}