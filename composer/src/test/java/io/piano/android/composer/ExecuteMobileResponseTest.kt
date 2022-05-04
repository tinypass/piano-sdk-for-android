package io.piano.android.composer

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.piano.android.composer.model.Data
import io.piano.android.composer.model.ExperienceResponse
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExecuteMobileResponseTest {
    private val moshi = Moshi.Builder()
        .add(ComposerJsonAdapterFactory())
        .add(EventJsonAdapterFactory())
        .build()

    @Test
    fun parseRawJson() {
        val adapter = moshi.adapter<Data<ExperienceResponse>>(
            Types.newParameterizedType(
                Data::class.java,
                ExperienceResponse::class.java
            )
        )
        val response = adapter.fromJson(RAW_JSON)
        assertNotNull(response)
        with(response) {
            assertTrue { errors.isEmpty() }
            assertTrue { data.result.events.size == 9 }
        }
    }

    companion object {
        private val RAW_JSON =
            """
            {"models":{"executionStatsContext":"N/A","result":{"experiences":[{"id":"EXIM3H50KA6G","title":
            "2. Mobile experience","updateDate":"2020-09-03T11:42:15.000+0000","version":2,"type":"mobile","status":
            "LIVE","schedule":null}],"debugMessages":["[Execute experience (pubId = EXIM3H50KA6G, updateDate = 
            2020-09-03 11:42:15.0)]","effectivePages5:Pages:[Target all pages, Conclusion: proceed, Execution time: 
            0 ms]","userSegment6:User segment:[Target all users: true, Conclusion: proceed, Execution time: 1 ms]",
            "showTemplate89666:Show template:[Conclusion: proceed, Execution time: 1 ms]","showLogin1CL3QUM26MJ18:Show 
            login:[Conclusion: proceed, Execution time: 1 ms]","pageViewMeterHYU3D66KGIY912:Pageview meter:
            [PageViewMeter counter with name = 'DefaultMeter' is not found - create new counter, PageView createDate = 
            2020-09-03T11:43:03.463, resetAfter = 1, MONTHS, expiration date = Optional[2020-10-03T11:43:03.463], 
            current time = 2020-09-03T11:43:03.470. Do we need reset counter: false, Referrer = '' (authority = ''), 
            current page = '/' (authority = ''). Is external referrer: false, Do not meter referrals check result is 
            true, Skip 'unique pages only' check, Unique pages only check result is true, Is need increment meter: true,
            Increment pageView meter views counter to value = 1, Add current page '/' into visited URL list, Total page 
            view count = 1, PageViewMeter type is PAGE_VIEWS, value before = 0, current value = 1, expire at = 2, should
            meter be incremented = true. Is page view meter expired: false, Conclusion: proceed, Execution time: 11 ms]"
            ,"pageViewMeterItem4I2B85H2ZLYS14:Meter value:[Is pageView meter expired: false, Conclusion: stop, Execution
            time: 1 ms]","nonSiteActionW9H2OL5M6SBS31:Non-site action:[Conclusion: proceed, Execution time: 0 ms]"],
            "events":[{"eventType":"userSegmentTrue","eventParams":{"callback":null},"eventModuleParams":{"moduleId":
            "userSegment6","moduleName":"User segment"},"eventExecutionContext":{"executionId":"1874qa565s-00002t2jorthc
            q2ofbf6vdgpss","experienceId":"EXIM3H50KA6G","experienceVersion":2,"trackingId":"N/A","splitTests":[],
            "currentMeterName":null,"activeMeters":[],"allMeters":[],"user":{"uid":"anon","firstName":"N/A","lastName":
            "N/A","email":"N/A"},"region":"NA","countryCode":"NA","accessList":[],"userSegments":{}},"eventConditions":
            []},{"eventType":"userSegmentFalse","eventParams":{"callback":null},"eventModuleParams":{"moduleId":
            "userSegment5","moduleName":"User segment"},"eventExecutionContext":{"executionId":
            "1874qa565s-00002t2jorthcq2ofbf6vdgpss","experienceId":"EXIM3H50KA6G","experienceVersion":2,"trackingId":
            "N/A","splitTests":[],"currentMeterName":null,"activeMeters":[],"allMeters":[],"user":{"uid":"anon",
            "firstName":"N/A","lastName":"N/A","email":"N/A"},"region":"NA","countryCode":"NA","accessList":[],
            "userSegments":{}},"eventConditions":[]},{"eventType":"showTemplate","eventParams":{"showCloseButton":true,
            "containerSelector":"","displayMode":"modal","templateId":"OTDWI5MZRXNP","templateVariantId":null,"delayBy":
            {"type":"time","value":0}},"eventModuleParams":{"moduleId":"showTemplate89666","moduleName":"Show template"}
            ,"eventExecutionContext":{"executionId":"1874qa565s-00002t2jorthcq2ofbf6vdgpss","experienceId":
            "EXIM3H50KA6G","experienceVersion":2,"trackingId":"N/A","splitTests":[],"currentMeterName":null,
            "activeMeters":[],"allMeters":[],"user":{"uid":"anon","firstName":"N/A","lastName":"N/A","email":"N/A"},
            "region":"NA","countryCode":"NA","accessList":[],"userSegments":{}},"eventConditions":[]},
            {"eventType":"showLogin","eventParams":{"userProvider":"piano_id"},"eventModuleParams":{"moduleId":
            "showLogin1CL3QUM26MJ18","moduleName":"Show login"},"eventExecutionContext":{"executionId":
            "1874qa565s-00002t2jorthcq2ofbf6vdgpss","experienceId":"EXIM3H50KA6G","experienceVersion":2,"trackingId":
            "N/A","splitTests":[],"currentMeterName":null,"activeMeters":[],"allMeters":[],"user":{"uid":"anon",
            "firstName":"N/A","lastName":"N/A","email":"N/A"},"region":"NA","countryCode":"NA","accessList":[],
            "userSegments":{}},"eventConditions":[]},{"eventType":"meterActive","eventParams":{"incremented":true,
            "callback":null,"meterName":"DefaultMeter","type":"pageViews","views":1,"viewsLeft":1,"maxViews":2,
            "totalViews":1},"eventModuleParams":{"moduleId":"pageViewMeterHYU3D66KGIY912","moduleName":"Pageview meter"
            },"eventExecutionContext":{"executionId":"1874qa565s-00002t2jorthcq2ofbf6vdgpss","experienceId":
            "EXIM3H50KA6G","experienceVersion":2,"trackingId":"N/A","splitTests":[],"currentMeterName":"DefaultMeter",
            "activeMeters":[{"meterName":"DefaultMeter","views":1,"viewsLeft":1,"maxViews":2,"totalViews":1}],
            "allMeters":[{"meterName":"DefaultMeter","views":1,"viewsLeft":null,"maxViews":null,"totalViews":1}],"user":
            {"uid":"anon","firstName":"N/A","lastName":"N/A","email":"N/A"},"region":"NA","countryCode":"NA",
            "accessList":[],"userSegments":{}},"eventConditions":[]},{"eventType":"meterExpired","eventParams":
            {"incremented":true,"callback":null,"meterName":"DefaultMeter2","type":"pageViews","views":2,"viewsLeft":0,
            "maxViews":2,"totalViews":2},"eventModuleParams":{"moduleId":"pageViewMeterHYU3D66KGIY912","moduleName":
            "Pageview meter"},"eventExecutionContext":{"executionId":"1874qa565s-00002t2jorthcq2ofbf6vdgpss",
            "experienceId":"EXIM3H50KA6G","experienceVersion":2,"trackingId":"N/A","splitTests":[],"currentMeterName":
            "DefaultMeter","activeMeters":[{"meterName":"DefaultMeter","views":1,"viewsLeft":1,"maxViews":2,
            "totalViews":1}],"allMeters":[{"meterName":"DefaultMeter","views":1,"viewsLeft":null,"maxViews":null,
            "totalViews":1}],"user":{"uid":"anon","firstName":"N/A","lastName":"N/A","email":"N/A"},"region":"NA",
            "countryCode":"NA","accessList":[],"userSegments":{}},"eventConditions":[]},{"eventType":"nonSite",
            "eventParams":{},"eventModuleParams":{"moduleId":"nonSiteActionW9H2OL5M6SBS31","moduleName":
            "Non-site action"},"eventExecutionContext":{"executionId":"1874qa565s-00002t2jorthcq2ofbf6vdgpss",
            "experienceId":"EXIM3H50KA6G","experienceVersion":2,"trackingId":"N/A","splitTests":[],"currentMeterName":
            "DefaultMeter","activeMeters":[],"allMeters":[{"meterName":"DefaultMeter","views":1,"viewsLeft":null,
            "maxViews":null,"totalViews":1}],"user":{"uid":"anon","firstName":"N/A","lastName":"N/A","email":"N/A"},
            "region":"NA","countryCode":"NA","accessList":[],"userSegments":{}},"eventConditions":[]},{"eventType":
            "experienceExecute","eventParams":{"accessList":[],"user":{"uid":"anon","firstName":"N/A","lastName":"N/A",
            "email":"N/A"}},"eventModuleParams":{"moduleId":"N/A","moduleName":"N/A"},"eventExecutionContext":
            {"executionId":"N/A","experienceId":"N/A","experienceVersion":null,"trackingId":"N/A","splitTests":[],
            "currentMeterName":"DefaultMeter","activeMeters":[],"allMeters":[{"meterName":"DefaultMeter","views":1,
            "viewsLeft":null,"maxViews":null,"totalViews":1}],"user":{"uid":"anon","firstName":"N/A","lastName":"N/A",
            "email":"N/A"},"region":"NA","countryCode":"NA","accessList":[],"userSegments":{}},"eventConditions":[]},
            {"eventType":"showRecommendations","eventParams":{"widgetId":"bb20a9e9183f2d20a2128bb40dcff74f4382ed9d",
            "placeholder":null,"containerSelector":"","displayMode":"modal","showCloseButton":true,"siteId":
            "1136341247897494322","type":"CXENSE"},"eventModuleParams":{"moduleId":"showRecommendationsZ4X8P92C5YE48",
            "moduleName":"Showrecommendations"},"eventExecutionContext":{"executionId":"18787j80ag-00002vosbc9k1uglo",
            "experienceId":"EXXE9I9CCKE0","experienceVersion":2,"trackingId":"{kpdx}AAAAAAAAAAAAAAAA","splitTests":[],
            "currentMeterName":null,"activeMeters":[],"allMeters":[],"user":{"uid":"anon","firstName":"N/A",
            "lastName":"N/A","email":"N/A","premium":"false"},"region":"NA","countryCode":"NA","accessList":[],
            "userSegments":{"STANDARD":{"segments":[],"expiresAt":0},"COMPOSER1X":{"segments":[],"expiresAt":0}},
            "creditStates":[]},"eventConditions":[]}],"swgEnabled":false},"xbc":{"cookie_value":"N/A","cookie_domain":
            null},"bid":"N/A","timezone_offset":-14400000,"tac":{"cookie_value":"","cookie_domain":null},"tbc":{
            "cookie_value":"N/A","cookie_domain":null}},"routes":{},"errors":[]}
            """.trimIndent().trim('\n')
    }
}
