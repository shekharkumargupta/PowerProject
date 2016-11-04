package com.opgea.crm.common;



public enum SetupsModuleQualifier {
	Console{
		public SetupsAttributeQualifier[] getAttributes(){return null;}
		
	},Calendar{
		public SetupsAttributeQualifier[] getAttributes(){return null;}
		
	},Lead{
		private SetupsAttributeQualifier attributes[]={SetupsAttributeQualifier.Source,SetupsAttributeQualifier.Grade,
				SetupsAttributeQualifier.Action,SetupsAttributeQualifier.Category,SetupsAttributeQualifier.Stage,
				SetupsAttributeQualifier.Probability,SetupsAttributeQualifier.IndustryType};
		public SetupsAttributeQualifier[] getAttributes(){return this.attributes;}
		
	},Customer{
		private SetupsAttributeQualifier attributes[]={SetupsAttributeQualifier.Source,SetupsAttributeQualifier.Grade,
				SetupsAttributeQualifier.Action,SetupsAttributeQualifier.Category,SetupsAttributeQualifier.Stage,SetupsAttributeQualifier.Probability};
		public SetupsAttributeQualifier[] getAttributes(){return this.attributes;}
		
	},Opportunity{
		private SetupsAttributeQualifier attributes[]={SetupsAttributeQualifier.Stage,SetupsAttributeQualifier.Status,
				SetupsAttributeQualifier.Probability};
		public SetupsAttributeQualifier[] getAttributes(){return this.attributes;}
	},Activity{
		
		private SetupsAttributeQualifier attributes[]={SetupsAttributeQualifier.Action,SetupsAttributeQualifier.Stage,
				SetupsAttributeQualifier.Priority,SetupsAttributeQualifier.Status};
		public SetupsAttributeQualifier[] getAttributes(){return this.attributes;}
		
	},Search{
		public SetupsAttributeQualifier[] getAttributes(){return null;}
	},Report{
		public SetupsAttributeQualifier[] getAttributes(){return null;}
	},Setups{
		public SetupsAttributeQualifier[] getAttributes(){return null;}
	},Sales{
		public SetupsAttributeQualifier[] getAttributes(){return null;}
	},
	
	
	Project{
		private SetupsAttributeQualifier attributes[] = {
							SetupsAttributeQualifier.Status,
							SetupsAttributeQualifier.Priority
							};
		public SetupsAttributeQualifier[] getAttributes(){
						return this.attributes;
					}
		},
		
	Issue{
			private SetupsAttributeQualifier attributes[] = {
															SetupsAttributeQualifier.Category, 
															SetupsAttributeQualifier.Priority,
															SetupsAttributeQualifier.Status,
															SetupsAttributeQualifier.Complexity,
															SetupsAttributeQualifier.Impact,
															SetupsAttributeQualifier.Resolution
															};
			public SetupsAttributeQualifier[] getAttributes(){
							return this.attributes;
						}
		}
		,
		Task{
			private SetupsAttributeQualifier attributes[] = {
															 
															SetupsAttributeQualifier.Priority,
															SetupsAttributeQualifier.Status,
															SetupsAttributeQualifier.Resolution
															};
			public SetupsAttributeQualifier[] getAttributes(){
							return this.attributes;
						}
		};		
	
	
	public abstract SetupsAttributeQualifier[] getAttributes();
}
