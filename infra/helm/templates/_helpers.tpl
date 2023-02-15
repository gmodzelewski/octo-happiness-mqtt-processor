{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "app.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{/* Default Labels: Helm recommended best-practice labels https://helm.sh/docs/chart_best_practices/labels/ */}}
{{- define "app.defaultLabels" }}
app.kubernetes.io/name: {{ include "app.fullname" . }}
app.kubernetes.io/namespace: {{ .Release.Namespace }}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version | replace "+" "_" }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/version: {{ .Chart.Version }}
app.kubernetes.io/app-version: {{ .Chart.AppVersion }}
app.openshift.io/runtime: quarkus
app.openshift.io/runtime-namespace: {{ .Release.Namespace }}
app.openshift.io/runtime-version: {{ .Chart.AppVersion }}
{{- end -}}

{{- define "app.defaultAnnotations" }}
app.openshift.io/vcs-uri: {{ coalesce (.Values.git).url "https://github.com/sa-mw-dach/octo-happiness-template-java" }}
{{- end -}}

{{- define "app.matchLabels" }}
app.kubernetes.io/name: {{ include "app.fullname" . }}
app.kubernetes.io/namespace: {{ .Release.Namespace }}
{{- end -}}